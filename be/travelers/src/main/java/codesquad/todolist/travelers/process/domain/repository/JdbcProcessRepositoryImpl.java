package codesquad.todolist.travelers.process.domain.repository;

import codesquad.todolist.travelers.process.domain.entity.Process;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcProcessRepositoryImpl implements ProcessRepository {

    private final NamedParameterJdbcTemplate template;

    public JdbcProcessRepositoryImpl(final NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Process> findProcesses() {
        String sql = "SELECT process_id, name "
                + "FROM process";

        return template.query(sql, processRowMapper());
    }

    @Override
    public String findProcessNameBy(final Long processId) {
        String sql = "SELECT name FROM process WHERE process_id = :processId";

        return template.queryForObject(sql, Map.of("processId", processId), String.class);
    }

    private RowMapper<Process> processRowMapper() {
        return ((rs, rowNum) -> new Process(
                rs.getLong("process_id"),
                rs.getString("name")
        ));
    }
}
