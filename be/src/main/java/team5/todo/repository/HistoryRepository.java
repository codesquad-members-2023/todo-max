package team5.todo.repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import team5.todo.domain.History;

@Repository
public class HistoryRepository {
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private final SimpleJdbcInsert jdbcInsert;

	public HistoryRepository(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		this.jdbcInsert = new SimpleJdbcInsert(dataSource)
			.withTableName("history")
			.usingColumns("title", "action", "origin", "destination", "at")
			.usingGeneratedKeyColumns("id");
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

	public List<History> findAll() {
		String sql = "SELECT title, action, origin, destination, at, created_at FROM history " +
				"ORDER BY created_at DESC LIMIT 20";
		return namedParameterJdbcTemplate.query(sql, historyRowMapper());
	}

	public void deleteAll() {
		String sql = "DELETE FROM history";
		namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource());
	}

	public Long save(History history) {
		return jdbcInsert.executeAndReturnKey(new BeanPropertySqlParameterSource(history)).longValue();
	}
}
