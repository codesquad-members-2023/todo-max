package team5.todo.repository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.sql.DataSource;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import team5.todo.annotation.RepositoryTest;
import team5.todo.domain.Action;
import team5.todo.domain.History;

@RepositoryTest
public class HistoryRepositoryTest {

	private final HistoryRepository historyRepository;
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public HistoryRepositoryTest(DataSource dataSource) {
		this.historyRepository = new HistoryRepository(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	private RowMapper<History> historyRowMapper() {
		return (rs, rowNum) -> History.builder()
			.title(rs.getString("title"))
			.action(rs.getString("action"))
			.destination(rs.getString("destination"))
			.origin(rs.getString("origin"))
			.at(rs.getString("at"))
			.createdAt(rs.getTimestamp("created_at").toLocalDateTime())
			.build();
	}

	@Test
	@DisplayName("전체 삭제 시 저장된 데이터를 모두 삭제한다.")
	void deleteAllTest() {
		//given
		//when
		historyRepository.deleteAll();

		//then
		assertThat(historyRepository.findAll().size()).isEqualTo(0);
	}

	@Test
	@DisplayName("저장된 히스토리를 불러올 때 그 개수가 20이 넘지 않아야 한다.")
	void findAllTest() {
		//given
		//when
		List<History> histories = historyRepository.findAll();

		//then
		assertThat(histories).size().isEqualTo(20);
	}

	@Test
	@DisplayName("테스트 히스토리가 history 테이블에 저장된다.")
	void saveTest() {
		//given
		History history = History.builder()
			.action(Action.MODIFY.getName())
			.title("먼지 산책시키기")
			.build();

		//when
		Long saveId = historyRepository.save(history);

		//then
		String sql = "SELECT title, action, origin, destination, at, created_at FROM history WHERE id = :id";
		MapSqlParameterSource params = new MapSqlParameterSource("id", saveId);
		List<History> foundHistory = namedParameterJdbcTemplate.query(sql, params, historyRowMapper());
		assertAll(
			() -> assertEquals(foundHistory.size(), 1),
			() -> assertEquals(foundHistory.get(0).getTitle(), "먼지 산책시키기"),
			() -> assertEquals(foundHistory.get(0).getAction(), Action.MODIFY.getName())

		);
	}
}
