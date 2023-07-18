package codesquad.todolist.travelers.process.domain.repository;

import codesquad.todolist.travelers.process.domain.entity.Process;
import java.util.List;
import java.util.Optional;

public interface ProcessRepository {
    List<Process> findProcesses();
    String findProcessNameBy(final Long processId);

    Optional<Long> createProcess(final Process process);
}
