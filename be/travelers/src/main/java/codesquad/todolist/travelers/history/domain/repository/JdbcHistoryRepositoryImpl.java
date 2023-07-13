package codesquad.todolist.travelers.history.domain.repository;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcHistoryRepositoryImpl implements HistoryRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public JdbcHistoryRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE FROM history";

        namedParameterJdbcTemplate.getJdbcTemplate().update(sql);
    }
}
