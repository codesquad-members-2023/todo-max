package codesquad.todolist.travelers.task.repository;

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
        String sql = "INSERT INTO task (title, contents, platform, process_id, position) "
                + "VALUES (:title, :contents, :platform, :processId, :position)";

        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(task);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(sql, param, keyHolder);
        return Optional.ofNullable(keyHolder.getKey()).map(Number::longValue);
    }

    @Override
    public void deleteBy(final Long taskId) {
        String sql = "UPDATE task " +
                "SET is_deleted = 1 " +
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
    public void updateTaskBy(final Long processId, final Long taskId, final double position) {
        String sql = "UPDATE task " +
                "SET process_id = :processId, position = :position " +
                "WHERE task_id = :taskId";

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("processId", processId)
                .addValue("position", position)
                .addValue("taskId", taskId);

        template.update(sql, param);
    }

    @Override
    public List<Task> findAllBy(final Long processId) {
        String sql = "SELECT task_id, title, contents, platform, position, process_id "
                + "FROM task "
                + "WHERE process_id = :processId AND is_deleted = 0 "
                + "ORDER BY position DESC";

        SqlParameterSource param = new MapSqlParameterSource("processId", processId);

        return template.query(sql, param, taskRowMapper());
    }

    @Override
    public Task findByIgnoringDeleted(final Long taskId) {
        String sql = "SELECT * FROM task WHERE task_id = :taskId";

        return template.queryForObject(sql, Map.of("taskId", taskId), taskRowMapper());
    }

    @Override
    public Long findPositionById(final Long taskId) {
        String sql = "SELECT position FROM task "
                + "WHERE task_id = :taskId";
        return template.queryForObject(sql, Map.of("taskId", taskId), Long.class);
    }

    @Override
    public void deleteByProcessId(final Long processId) {
        String sql = "UPDATE task SET is_deleted = 1 WHERE process_id = :processId";
        template.update(sql, Map.of("processId", processId));
    }

    private RowMapper<Task> taskRowMapper() {
        return ((rs, rowNum) -> new Task(
                rs.getLong("task_id"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getString("platform"),
                rs.getLong("process_id"),
                rs.getDouble("position")
        ));
    }
}
