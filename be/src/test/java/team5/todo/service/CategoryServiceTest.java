package team5.todo.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import team5.todo.annotation.ServiceTest;
import team5.todo.controller.dto.CardResponse;
import team5.todo.controller.dto.CategoryResponse;
import team5.todo.domain.Card;
import team5.todo.domain.Category;
import team5.todo.repository.CardRepository;
import team5.todo.repository.CategoryRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ServiceTest
public class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private CardRepository cardRepository;

    @DisplayName("레포지토리에서 Card와 Category를 가져와서 CategoryResponse를 만든다.")
    @Test
    void getAllCategories() {
        given(categoryRepository.findAll()).willReturn(createDummyCategories());
        given(cardRepository.findAll()).willReturn(createDummyCards());

        List<CategoryResponse> actual = categoryService.getAllCategories();

        assertThat(actual).isInstanceOf(List.class);
        assertThat(actual.size()).isEqualTo(4);
        assertThat(actual.get(0).getCardResponses().size()).isEqualTo(1);
        assertThat(actual.get(1).getCardResponses().size()).isEqualTo(2);
        assertThat(actual.get(2).getCardResponses().size()).isEqualTo(3);
        assertThat(actual.get(3).getCardResponses().size()).isEqualTo(4);
    }

    private List<Category> createDummyCategories() {
        Category category1 = Category.builder()
                .id(1L)
                .name("더미데이터 1")
                .build();
        Category category2 = Category.builder()
                .id(2L)
                .name("더미데이터 2")
                .build();
        Category category3 = Category.builder()
                .id(3L)
                .name("더미데이터 3")
                .build();
        Category category4 = Category.builder()
                .id(4L)
                .name("더미데이터 4")
                .build();
        return List.of(category1, category2, category3, category4);
    }

    private List<Card> createDummyCards() {
        Card card1 = Card.builder()
                .id(1L)
                .categoryId(1L)
                .title("타이틀1")
                .contents("내용1")
                .position(1000D)
                .build();
        Card card2 = Card.builder()
                .id(2L)
                .categoryId(2L)
                .title("타이틀2")
                .contents("내용2")
                .position(2000D)
                .build();
        Card card3 = Card.builder()
                .id(3L)
                .categoryId(2L)
                .title("타이틀3")
                .contents("내용3")
                .position(3000D)
                .build();
        Card card4 = Card.builder()
                .id(4L)
                .categoryId(3L)
                .title("타이틀4")
                .contents("내용4")
                .position(4000D)
                .build();
        Card card5 = Card.builder()
                .id(5L)
                .categoryId(3L)
                .title("타이틀5")
                .contents("내용5")
                .position(5000D)
                .build();
        Card card6 = Card.builder()
                .id(6L)
                .categoryId(3L)
                .title("타이틀6")
                .contents("내용6")
                .position(6000D)
                .build();
        Card card7 = Card.builder()
                .id(7L)
                .categoryId(4L)
                .title("타이틀7")
                .contents("내용7")
                .position(7000D)
                .build();
        Card card8 = Card.builder()
                .id(8L)
                .categoryId(4L)
                .title("타이틀8")
                .contents("내용8")
                .position(8000D)
                .build();
        Card card9 = Card.builder()
                .id(9L)
                .categoryId(4L)
                .title("타이틀9")
                .contents("내용9")
                .position(9000D)
                .build();
        Card card10 = Card.builder()
                .id(10L)
                .categoryId(4L)
                .title("타이틀10")
                .contents("내용10")
                .position(10000D)
                .build();
        return List.of(card1, card2, card3, card4, card5, card6, card7, card8, card9, card10);
    }


}
