package team5.todo.repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import team5.todo.domain.Category;

@Repository
public class CategoryRepository {
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public CategoryRepository(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	private RowMapper<Category> categoryRowMapper() {
		return (rs, rowNum) ->
			Category.builder()
				.id(rs.getLong("id"))
				.name(rs.getString("name"))
				.build();
	}

	public List<Category> findAll() {
		String sql = "SELECT id, name FROM category ORDER BY id ASC";
		return namedParameterJdbcTemplate.query(sql, categoryRowMapper());
	}

	public Category findById(long id) {
		String sql = "SELECT id, name FROM category WHERE id = :id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		return namedParameterJdbcTemplate.queryForObject(sql, params, categoryRowMapper());
	}
}
