package codesquad.todolist.travelers.history.repository;

import codesquad.todolist.travelers.history.domain.entity.History;
import codesquad.todolist.travelers.history.domain.vo.ActionHistory;
import java.util.List;
import java.util.Optional;

public interface HistoryRepository {
    void deleteAll();

    List<ActionHistory> findAll();

    Optional<Long> save(final History history);
}
