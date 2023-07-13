package codesquad.todolist.travelers.task.domain.repository;

import codesquad.todolist.travelers.task.domain.entity.Process;
import codesquad.todolist.travelers.task.domain.entity.Task;
import java.util.List;

public interface TaskRepository {
    Long save(final Task task);

    void deleteBy(final Long taskId);

    void updateBy(final Long taskId, final Task task);

    List<Task> findAllBy(final Long processId);

    List<Process> findProcesses();
}
