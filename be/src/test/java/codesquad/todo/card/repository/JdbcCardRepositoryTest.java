package codesquad.todo.card.repository;

import java.util.List;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Repository;

import codesquad.todo.card.entity.Card;

// Repository 애노테이션이 붙은 클래스만 빈으로 등록
@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Repository.class))
// Replace.NONE으로 설정하면 @ActiveProfiles에 설정한 프로파일 환경값에 따라 데이터소스가 적용된다.
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class JdbcCardRepositoryTest {

	@Autowired
	private CardRepository cardRepository;

	@Test
	@DisplayName("모든 카드 데이터를 요청합니다.")
	public void testFindAll() {
		// given

		// when
		List<Card> cards = cardRepository.findAll();
		// then
		SoftAssertions.assertSoftly(softAssertions -> {
			softAssertions.assertThat(cards.size()).isEqualTo(9);
			softAssertions.assertAll();
		});
	}

	@Test
	@DisplayName("컬럼 아이디에 따른 카드 데이터를 요청합니다.")
	public void testFindAllByColumnId() {
		// given

		// when
		List<Card> cards = cardRepository.findAllByColumnId(1L);
		// then
		SoftAssertions.assertSoftly(softAssertions -> {
			softAssertions.assertThat(cards.size()).isEqualTo(3);
			softAssertions.assertAll();
		});
	}
}
