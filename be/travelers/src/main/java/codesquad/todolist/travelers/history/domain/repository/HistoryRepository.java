package codesquad.todolist.travelers.history.domain.repository;

import codesquad.todolist.travelers.history.domain.ActionHistory;
import codesquad.todolist.travelers.history.domain.entity.History;
import java.util.List;
import java.util.Optional;

public interface HistoryRepository {
    void deleteAll();

    List<ActionHistory> findAll();

    Optional<Long> save(final History history);
}
