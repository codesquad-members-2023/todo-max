package codesquad.todolist.travelers.task.domain.repository;

import static org.assertj.core.api.Assertions.assertThat;

import codesquad.todolist.travelers.annotation.RepositoryTest;
import codesquad.todolist.travelers.task.domain.entity.Task;
import codesquad.todolist.travelers.task.repository.JdbcTaskRepositoryImpl;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@RepositoryTest
class JdbcTaskRepositoryImplTest {

    @Autowired
    private JdbcTaskRepositoryImpl repository;

    // 설리
    // 1. 등록
    @Test
    @DisplayName("save()를 통해 task를 DB에 저장할 수 있다.")
    void saveSuccessTest() {
        // given
        Task expected = dummyTask();

        // when
        Long taskId = repository.save(expected).orElseThrow(() ->
                new NoSuchElementException("save 후 taskId가 반환되지 않음"));

        // then
        Task actual = repository.findByIgnoringDeleted(taskId);

        assertThat(actual)
                // isEqualTo로는 주소 비교를 해버리니 값 하나씩 재귀적으로 돌면서 비교해주는 메서드 사용요
                .usingRecursiveComparison()
                // DB에서 생성해주는 필드는 무시하도록 명시
                .ignoringFields("taskId")
                .isEqualTo(expected);
    }

    private Task dummyTask() {
        return new Task(null, "제목1", "내용1", "web", 1L, 0);
    }

    // 2. 삭제
    @Test
    @DisplayName("soft-delete 후 find를 할 때 가져올 수 없으면 삭제가 완료된 것이라 판단할 수 있다.")
    void deleteSuccessTest() {
        // given
        Task dummy = dummyTask();
        Long taskId = repository.save(dummy).orElseThrow(() ->
                new NoSuchElementException("save 후 taskId가 반환되지 않음"));

        // when
        repository.deleteBy(taskId);

        // then
        List<Task> data = repository.findAllBy(1L);

        assertThat(data).isEmpty();
    }

    // 지안
    // 1. 수정
    @Test
    @DisplayName("updateBy()를 통해 task를 DB에 업데이트(수정) 할 수 있다.")
    void updateTest() {
        // given
        Long taskId = repository.save(dummyOldTask()).orElseThrow(() ->
                new NoSuchElementException("save 후 taskId가 반환되지 않음"));
        Task expected = dummyTask();

        // when
        repository.updateBy(taskId, expected);

        // then
        Task actual = repository.findByIgnoringDeleted(taskId);

        assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("taskId")
                .isEqualTo(expected);
    }

    Task dummyOldTask() {
        return new Task(3L, "old title", "old contents", "web", 1L, 0);
    }

    // 2. 이동
    @Test
    @DisplayName("updateBy()를 통해 task를 DB에 업데이트(이동) 할 수 있다.")
    void moveTest() {
        // given
        Long position1 = repository.save(dummyTaskPosition1()).orElseThrow(() ->
                new NoSuchElementException("save 후 taskId가 반환되지 않음"));
        Long position3 = repository.save(dummyTaskPosition3()).orElseThrow(() ->
                new NoSuchElementException("save 후 taskId가 반환되지 않음"));
        Long expectedId = repository.save(dummyTask()).orElseThrow(() ->
                new NoSuchElementException("save 후 taskId가 반환되지 않음"));

        // when
        long expectedPosition = repository.findPositionById(position1) + repository.findPositionById(position3) / 2;
        Task expected = repository.findByIgnoringDeleted(expectedId);
        repository.updateTaskBy(expected.getProcessId(), expected.getTaskId(), expectedPosition);

        // then
        Task actual = repository.findByIgnoringDeleted(expected.getTaskId());

        assertThat(actual.getPosition()).isEqualTo(expectedPosition);
    }

    Task dummyTaskPosition1() {
        return new Task(3L, "제목1", "내용1", "web", 1L, 1.0);
    }

    Task dummyTaskPosition3() {
        return new Task(3L, "제목1", "내용1", "web", 1L, 3.0);
    }
}