package team5.todo.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import team5.todo.annotation.ControllerTest;
import team5.todo.controller.dto.CardResponse;
import team5.todo.controller.dto.CategoryResponse;
import team5.todo.service.CategoryService;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ControllerTest(CategoryController.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CategoryService categoryService;

    @DisplayName("전체 조회를 했을 때 카테고리와 카드를 반환한다.")
    @Test
    void getAllCategories() throws Exception {
        given(categoryService.getAllCategories()).willReturn(createDummyCategoryResponses());

        ResultActions resultActions = mockMvc.perform(get("/index"));

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$.[0].id").value(1L))
                .andExpect(jsonPath("$.[1].name").value("2번 카테고리"))
                .andExpect(jsonPath("$.[2].cards.length()").value(4))
                .andExpect(jsonPath("$.[2].cards.[0].id").value(4L))
                .andExpect(jsonPath("$.[2].cards.[2].contents")
                        .value("이것은 6번 카드의 내용입니다."))
                .andDo(print());
    }

    private List<CategoryResponse> createDummyCategoryResponses() {
        Map<Long, List<CardResponse>> cardResponses = createDummyCardResponses();

        CategoryResponse categoryResponse1 = CategoryResponse.builder()
                .id(1L)
                .name("1번 카테고리")
                .cardResponses(cardResponses.get(1L))
                .build();
        CategoryResponse categoryResponse2 = CategoryResponse.builder()
                .id(2L)
                .name("2번 카테고리")
                .cardResponses(cardResponses.get(2L))
                .build();
        CategoryResponse categoryResponse3 = CategoryResponse.builder()
                .id(3L)
                .name("1번 카테고리")
                .cardResponses(cardResponses.get(3L))
                .build();
        return List.of(categoryResponse1, categoryResponse2, categoryResponse3);
    }


    private Map<Long, List<CardResponse>> createDummyCardResponses() {
        CardResponse cardResponse1 = CardResponse.builder()
                .id(1L)
                .categoryId(1L)
                .title("1번 카드입니다.")
                .contents("이것은 1번 카드의 내용입니다.")
                .build();
        CardResponse cardResponse2 = CardResponse.builder()
                .id(2L)
                .categoryId(1L)
                .title("2번 카드입니다.")
                .contents("이것은 2번 카드의 내용입니다.")
                .build();
        CardResponse cardResponse3 = CardResponse.builder()
                .id(3L)
                .categoryId(2L)
                .title("3번 카드입니다.")
                .contents("이것은 3번 카드의 내용입니다.")
                .build();
        CardResponse cardResponse4 = CardResponse.builder()
                .id(4L)
                .categoryId(3L)
                .title("4번 카드입니다.")
                .contents("이것은 4번 카드의 내용입니다.")
                .build();
        CardResponse cardResponse5 = CardResponse.builder()
                .id(5L)
                .categoryId(3L)
                .title("5번 카드입니다.")
                .contents("이것은 5번 카드의 내용입니다.")
                .build();
        CardResponse cardResponse6 = CardResponse.builder()
                .id(6L)
                .categoryId(3L)
                .title("6번 카드입니다.")
                .contents("이것은 6번 카드의 내용입니다.")
                .build();
        CardResponse cardResponse7 = CardResponse.builder()
                .id(7L)
                .categoryId(3L)
                .title("7번 카드입니다.")
                .contents("이것은 7번 카드의 내용입니다.")
                .build();
        List<CardResponse> cardResponseList = List.of(cardResponse1, cardResponse2,
                cardResponse3, cardResponse4, cardResponse5, cardResponse6, cardResponse7);
        return cardResponseList.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.groupingBy(CardResponse::getCategoryId),
                        Collections::unmodifiableMap));

    }

}
