package codesquad.todolist.travelers.task.domain.repository;

import codesquad.todolist.travelers.task.domain.entity.Process;
import codesquad.todolist.travelers.task.domain.entity.Task;
import java.util.List;
import java.util.Objects;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcTaskRepositoryImpl implements TaskRepository {
    private final NamedParameterJdbcTemplate template;

    public JdbcTaskRepositoryImpl(final NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    @Override
    public Long save(final Task task) {
        // INSERT문에서는 JOIN 필요 X
        String sql = "INSERT INTO task (task_id, title, contents, platform, created_time, process_id) "
                + "VALUES (:taskId, :title, :contents, :platform, :createdTime, :processId)";

        SqlParameterSource param = new BeanPropertySqlParameterSource(task);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(sql, param, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public List<Task> findAllBy(final Long processId) {
        String sql = "SELECT t.task_id, t.title, t.contents, t.platform, t.created_time, t.process_id, p.name "
                + "FROM task t "
                + "JOIN process p ON t.process_id = p.process_id "
                + "WHERE t.process_id = :processId";

        SqlParameterSource param = new MapSqlParameterSource("processId", processId);

        return template.query(sql, param, taskRowMapper());
    }

    @Override
    public List<Process> findProcesses() {
        String sql = "SELECT process_id, name "
                + "FROM process";

        return template.query(sql, processRowMapper());
    }

    private RowMapper<Task> taskRowMapper() {
        return ((rs, rowNum) -> new Task(
                rs.getLong("task_id"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getString("platform"),
                rs.getDate("created_time").toString(),
                rs.getLong("process_id")
        ));
    }

    private RowMapper<Process> processRowMapper() {
        return ((rs, rowNum) -> new Process(
                rs.getLong("process_id"),
                rs.getString("name")
        ));
    }
}
