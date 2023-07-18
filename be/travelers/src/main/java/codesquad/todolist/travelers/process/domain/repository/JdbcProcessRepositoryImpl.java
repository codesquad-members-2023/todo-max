package codesquad.todolist.travelers.process.domain.repository;

import codesquad.todolist.travelers.process.domain.entity.Process;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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

    @Override
    public Optional<Long> createProcess(final Process process) {
        String sql = "INSERT INTO process(name) VALUES (:name)";
        SqlParameterSource param = new MapSqlParameterSource("name", process.getName());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(sql, param, keyHolder);

        return Optional.ofNullable(keyHolder.getKey()).map(Number::longValue);
    }

    @Override
    public Optional<Process> findProcessById(Long processId) {
        String sql = "SELECT process_id, name FROM process WHERE process_id = :processId";

        return template.query(sql, Map.of("processId", processId), processRowMapper()).stream().findFirst();
    }

    @Override
    public void updateProcess(Process process) {
        String sql = "UPDATE process SET name = :name WHERE process_id = :processId";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", process.getName());
        params.addValue("processId", process.getProcessId());
        template.update(sql, params);
    }

    private RowMapper<Process> processRowMapper() {
        return ((rs, rowNum) -> new Process(
                rs.getLong("process_id"),
                rs.getString("name")
        ));
    }
}
