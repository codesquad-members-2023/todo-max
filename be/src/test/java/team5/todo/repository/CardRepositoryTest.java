package team5.todo.repository;


import static java.util.stream.Collectors.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


import java.util.List;

import javax.sql.DataSource;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import team5.todo.annotation.RepositoryTest;
import team5.todo.controller.dto.CardMoveRequest;
import team5.todo.domain.Card;

@RepositoryTest
public class CardRepositoryTest {

	private final CardRepository cardRepository;
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public CardRepositoryTest(DataSource dataSource) {
		this.cardRepository = new CardRepository(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Test
	@DisplayName("저장되어 있는 모든 카드를 포지션 값이 큰 카드부터 반환한다")
	void findAllTest() {
		//given
		//when
		List<Card> actualCards = cardRepository.findAll();
		//then

		assertThat(actualCards.size()).isEqualTo(10);
		List<Double> positions = actualCards.stream()
			.map(Card::getPosition)
			.collect(toList());

		boolean isDecending = positions.stream()
			.sorted((a, b) -> b.compareTo(a))
			.collect(toList())
			.equals(positions);

		assertThat(isDecending).isTrue();
	}

	@Test
	@DisplayName("테스트 데이터가 card 테이블에 저장된다.")

	void saveCardTest() {
		//given
		String testTitle = "save test1";
		String testContents = "save test contents";
		Card card = Card.builder()
			.categoryId(1L)
			.title(testTitle)
			.contents(testContents)
			.build();
		String sql = "SELECT IFNULL(MAX(position), 0) FROM card WHERE category_id = 1";
		Double maxPosition = namedParameterJdbcTemplate.queryForObject(sql, new MapSqlParameterSource(), Double.class);

		//when
		Long saveResultId = cardRepository.save(card);

		//then
		Card actual = cardRepository.findById(saveResultId);

		assertAll(
			() -> assertEquals(1L, actual.getCategoryId()),
			() -> assertEquals(maxPosition + CardRepository.getGapValue(), actual.getPosition()),
			() -> assertEquals(testTitle, actual.getTitle()),
			() -> assertEquals(testContents, actual.getContents())
		);
	}

	@Test
	@DisplayName("카드를 삭제했을 때, 저장한 카드가 삭제된다.")
	void deleteCardTest() {
		Card card = Card.builder()
			.categoryId(1L)
			.title("save test1")
			.contents("save test contents")
			.build();
		Long saveResultId = cardRepository.save(card);

		cardRepository.delete(saveResultId);

		assertThatThrownBy(() -> cardRepository.findById(saveResultId))
			.isInstanceOf(EmptyResultDataAccessException.class);
	}

	@Test
	@DisplayName("카드를 저장했을 때, 저장한 카드를 수정하면 수정된 데이터가 db에 반영된다.")
	void updateCardTest() {
		//given
		Card original = Card.builder()
			.categoryId(1L)
			.title("original title")
			.contents("original contents")
			.build();

		Long saveResultId = cardRepository.save(original);

		String expectedTitle = "new title";
		String expectedContents = "new contents";
		Card updateRequest = Card.builder()
			.id(saveResultId)
			.title(expectedTitle)
			.contents(expectedContents)
			.build();

		//when
		cardRepository.modify(updateRequest);

		//then
		Card actual = cardRepository.findById(saveResultId);
		assertAll(
			() -> assertEquals(expectedTitle, actual.getTitle()),
			() -> assertEquals(expectedContents, actual.getContents())
		);
	}

	@Test
	@DisplayName("2개의 카드 사이로 이동시 2 카드의 포지션 값 평균을 카테고리 값과 함께 넣는다.")
	void moveWithBothCardsTest() {
		//given
		CardMoveRequest cardMoveRequest = CardMoveRequest.builder()
			.id(1L)
			.beforeCardId(2L)
			.afterCardId(3L)
			.categoryId(1L)
			.build();
		Card beforeCard = cardRepository.findById(cardMoveRequest.getBeforeCardId());
		Card afterCard = cardRepository.findById(cardMoveRequest.getAfterCardId());
		double avgPosition = (beforeCard.getPosition() + afterCard.getPosition()) / 2;
		//when
		cardRepository.moveWithBothCards(cardMoveRequest);

		//then

		Card movedCard = cardRepository.findById(cardMoveRequest.getId());
		assertThat(movedCard.getPosition()).isEqualTo(avgPosition);
		assertThat(movedCard.getCategoryId()).isEqualTo(cardMoveRequest.getCategoryId());
	}

	@Test
	@DisplayName("위(앞) 카드가 없는 곳으로 이동시 해당 카데고리의 position값이 가장 큰 값에 갭 값을 더한 값과 해당 카테고리 id도 수정한다")
	void moveWithBeforeCardTest() {
		//given
		CardMoveRequest cardMoveRequest = CardMoveRequest.builder()
			.id(1L)
			.beforeCardId(3L)
			.afterCardId(null)
			.categoryId(3L)
			.build();
		Card beforeCard = cardRepository.findById(cardMoveRequest.getBeforeCardId());
		double newPosition = beforeCard.getPosition() + CardRepository.getGapValue();
		//when
		cardRepository.moveWithBeforeCard(cardMoveRequest);

		//then

		Card movedCard = cardRepository.findById(cardMoveRequest.getId());
		assertThat(movedCard.getPosition()).isEqualTo(newPosition);
		assertThat(movedCard.getCategoryId()).isEqualTo(cardMoveRequest.getCategoryId());
	}

	@Test
	@DisplayName("아래에 아무 카드도 없는 곳으로 이동시 위에 있는 카드 포지션 값의 절반을 더해준다")
	void moveWithAfterCardTest() {
		//given
		CardMoveRequest cardMoveRequest = CardMoveRequest.builder()
			.id(1L)
			.afterCardId(3L)
			.beforeCardId(null)
			.categoryId(3L)
			.build();

		Card afterCard = cardRepository.findById(cardMoveRequest.getAfterCardId());
		double newPosition = afterCard.getPosition() / 2;

		//when
		cardRepository.moveWithAfterCard(cardMoveRequest);

		//then
		Card movedCard = cardRepository.findById(cardMoveRequest.getId());
		assertThat(movedCard.getPosition()).isEqualTo(newPosition);

	}

	@Test
	@DisplayName("아무런 카드가 없는 카테고리로 이동시 포지션 값을 기본 갭 값으로 변경한다")
	void moveWoBothCardsTest() {
		//given
		CardMoveRequest cardMoveRequest = CardMoveRequest.builder()
			.id(1L)
			.beforeCardId(null)
			.afterCardId(null)
			.categoryId(2L)
			.build();
		double newPosition = CardRepository.getGapValue();

		//when
		cardRepository.moveWoBothCard(cardMoveRequest);

		//then

		Card movedCard = cardRepository.findById(cardMoveRequest.getId());
		assertThat(movedCard.getPosition()).isEqualTo(newPosition);
		assertThat(movedCard.getCategoryId()).isEqualTo(cardMoveRequest.getCategoryId());
	}
}
