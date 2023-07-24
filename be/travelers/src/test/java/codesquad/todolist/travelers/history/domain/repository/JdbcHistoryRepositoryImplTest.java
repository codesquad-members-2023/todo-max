package codesquad.todolist.travelers.history.domain.repository;

import codesquad.todolist.travelers.annotation.RepositoryTest;
import codesquad.todolist.travelers.history.domain.entity.History;
import codesquad.todolist.travelers.history.domain.vo.ActionHistory;
import codesquad.todolist.travelers.history.repository.JdbcHistoryRepositoryImpl;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@RepositoryTest
class JdbcHistoryRepositoryImplTest {

    @Autowired
    private JdbcHistoryRepositoryImpl repository;

    @Test
    @DisplayName("deleteAllHistory() 를 통해 모든 활동기록을 지울수 있다.")
    void deleteAllHistoryTest() {
        //given
        repository.save(dummyHistory1());

        //when
        repository.deleteAll();

        //then
        List<ActionHistory> actual = repository.findAll();
        Assertions.assertThat(actual.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("findAll() 을 통해 모든 활동 기록을 조회 할수있다.")
    void findAllTest() throws InterruptedException {
        //given
        List<History> histories = Arrays.asList(dummyHistory1(), dummyHistory2());
        repository.save(histories.get(0));
        Thread.sleep(2000);
        repository.save(histories.get(1));

        //when
        List<ActionHistory> actual = repository.findAll();

        //then
        Assertions.assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("createdTime")
                .ignoringFields("imageUrl")
                .isEqualTo(dummyActionHistoryList());
    }

    private History dummyHistory1() {
        return new History.Builder()
                .title("블로그에 포스팅할 것")
                .from("하고있는 일")
                .actionId(3L)
                .userId(1L)
                .build();
    }

    private History dummyHistory2() {
        return new History.Builder()
                .title("블로그에 포스팅할 것")
                .from("하고있는 일")
                .to("해야할 일")
                .actionId(1L)
                .userId(1L)
                .build();
    }

    private List<ActionHistory> dummyActionHistoryList() {
        return Arrays.asList(dummyActionHistoryForMove(), dummyActionHistoryForPost());
    }

    private ActionHistory dummyActionHistoryForPost() {
        return new ActionHistory("블로그에 포스팅할 것", "하고있는 일", "", "등록", LocalDateTime.now(), "anonymous", "");
    }

    private ActionHistory dummyActionHistoryForMove() {
        return new ActionHistory("블로그에 포스팅할 것", "하고있는 일", "해야할 일", "이동", LocalDateTime.now(), "anonymous", "");
    }

}