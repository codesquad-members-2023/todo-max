package team5.todo.controller.dto;

import team5.todo.domain.Card;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CardSaveRequest {

    @NotNull(message = "카테고리 id를 넣어주세요.")
    private final Long categoryId;
    @Size(min = 1, max = 64, message = "제목은 1~64자 이하여야 합니다.")
    private final String title;
    @Size(min = 1, max = 500, message = "내용은 1~500자 이하여야 합니다.")
    private final String contents;

    public static class Builder {

        private Long categoryId;
        private String title;
        private String contents;

        private Builder() {}

        public Builder categoryId(Long categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder contents(String contents) {
            this.contents = contents;
            return this;
        }

        public CardSaveRequest build() {
            return new CardSaveRequest(this);
        }
    }

    private CardSaveRequest(Long categoryId, String title, String contents) {
        this.categoryId = categoryId;
        this.title = title;
        this.contents = contents;
    }

    private CardSaveRequest(Builder builder) {
        this.categoryId = builder.categoryId;
        this.title = builder.title;
        this.contents = builder.contents;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Card toCard() {
        return Card.builder()
                .categoryId(categoryId)
                .title(title)
                .contents(contents)
                .build();
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }
}
