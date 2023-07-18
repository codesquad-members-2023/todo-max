package codesquad.todolist.travelers.task.domain.repository;

import codesquad.todolist.travelers.task.domain.entity.Task;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
    public Optional<Long> save(final Task task) {
        // INSERT문에서는 JOIN 필요 X
        String sql = "INSERT INTO task (title, contents, platform, process_id) "
                + "VALUES (:title, :contents, :platform, :processId)";

        SqlParameterSource param = new BeanPropertySqlParameterSource(task);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(sql, param, keyHolder);
        return Optional.ofNullable(keyHolder.getKey()).map(Number::longValue);
    }

    @Override
    public void deleteBy(final Long taskId) {
        String sql = "DELETE FROM task " +
                "WHERE task_id = :taskId";

        template.update(sql, Map.of("taskId", taskId));
    }

    @Override
    public void updateBy(final Long taskId, final Task task) {
        String sql = "UPDATE task " +
                "SET title = :title, contents = :contents " +
                "WHERE task_id = :taskId";

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("title", task.getTitle())
                .addValue("contents", task.getContents())
                .addValue("taskId", taskId);

        template.update(sql, param);
    }

    @Override
    public void updateTaskBy(Long processId, Long taskId) {
        String sql = "UPDATE task " +
                "SET process_id = :processId " +
                "WHERE task_id = :taskId";

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("processId", processId)
                .addValue("taskId", taskId);

        template.update(sql, param);
    }

    @Override
    public List<Task> findAllBy(final Long processId) {
        String sql = "SELECT task_id, title, contents, platform, created_time, process_id "
                + "FROM task "
                + "WHERE process_id = :processId "
                + "ORDER BY created_time DESC";

        SqlParameterSource param = new MapSqlParameterSource("processId", processId);

        return template.query(sql, param, taskRowMapper());
    }

    @Override
    public Task findBy(final Long taskId) {
        String sql = "SELECT * FROM task WHERE task_id = :taskId";

        return template.queryForObject(sql, Map.of("taskId", taskId), taskRowMapper());
    }

    private RowMapper<Task> taskRowMapper() {
        return ((rs, rowNum) -> new Task(
                rs.getLong("task_id"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getString("platform"),
                rs.getTimestamp("created_time").toLocalDateTime(),
                rs.getLong("process_id")
        ));
    }
}
