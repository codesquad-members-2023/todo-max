package team5.todo.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import team5.todo.annotation.IntegrationTest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
public class CategoryIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("카테고리와 카드를 다 조회한다")
    @Test
    void categoryIntegrationTest() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/index"))
                .andDo(print());

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$.[2].name").value("완료한 일"))
                .andExpect(jsonPath("$.[1].id").value(2))
                .andExpect(jsonPath("$.[0].cards.length()").value(3))
                .andExpect(jsonPath("$.[1].cards.[1].contents").value("내용5"))
                .andExpect(jsonPath("$.[2].cards.[2].title").value("제목1"));

    }
}
