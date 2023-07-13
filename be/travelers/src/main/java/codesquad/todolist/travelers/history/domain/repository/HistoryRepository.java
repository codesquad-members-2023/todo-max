package codesquad.todolist.travelers.history.domain.repository;

import codesquad.todolist.travelers.history.domain.ActionHistory;
import java.util.List;

public interface HistoryRepository {
    void deleteAll();

    List<ActionHistory> findAll();
}
