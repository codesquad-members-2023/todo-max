package codesquad.todolist.travelers.history.domain.repository;

import codesquad.todolist.travelers.history.domain.ActionHistory;
import codesquad.todolist.travelers.history.domain.entity.History;
import java.util.List;
import java.util.Objects;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcHistoryRepositoryImpl implements HistoryRepository {
    private final NamedParameterJdbcTemplate template;

    public JdbcHistoryRepositoryImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }


    @Override
    public void deleteAll() {
        String sql = "DELETE FROM history";

        template.getJdbcTemplate().update(sql);
    }

    @Override
    public List<ActionHistory> findAll() {
        String sql =
                "SELECT h.title, h.created_time, h.from, h.to, a.name AS action_name, u.name AS user_name, u.image_url "
                        + "FROM history h "
                        + "JOIN action a ON a.action_id = h.action_id "
                        + "JOIN user u ON u.user_id = h.user_id "
                        + "ORDER BY h.created_time DESC ";

        return template.query(sql, ActionhistoryRowMapper());
    }

    @Override
    public Long save(final History history) {
        String sql = "INSERT INTO history (title, `from`, `to`, action_id, user_id) "
                + "VALUES (:title, IFNULL(:from,''), IFNULL(:to,''), :actionId, :userId)";

        SqlParameterSource param = new BeanPropertySqlParameterSource(history);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(sql, param, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    private RowMapper<ActionHistory> ActionhistoryRowMapper() {
        return ((rs, rowNum) -> new ActionHistory(
                rs.getString("title"),
                rs.getString("from"),
                rs.getString("to"),
                rs.getString("action_name"),
                rs.getTimestamp("created_time").toLocalDateTime(),
                rs.getString("user_name"),
                rs.getString("image_url")
        ));
    }

}
