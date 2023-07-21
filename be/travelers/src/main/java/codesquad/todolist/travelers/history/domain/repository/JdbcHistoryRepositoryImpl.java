package codesquad.todolist.travelers.history.domain.repository;

import codesquad.todolist.travelers.history.domain.ActionHistory;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
                "SELECT h.title, h.from, h.to, h.created_time, a.name AS action_name, u.name AS user_name, u.image_url "
                        + "FROM history h "
                        + "JOIN action a ON a.action_id = h.action_id "
                        + "JOIN user u ON u.user_id = h.user_id "
                        + "ORDER BY h.created_time DESC ";

        return template.query(sql, ActionhistoryRowMapper());
    }

    /**
     * 첫번째 name: action 테이블의 name, 두번째 name: user 테이블의 name
     *
     * @return
     */
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
