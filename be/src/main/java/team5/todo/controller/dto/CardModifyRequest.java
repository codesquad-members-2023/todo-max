package team5.todo.controller.dto;

import team5.todo.domain.Card;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CardModifyRequest {

    @NotNull(message = "카드 id를 넣어주세요.")
    private final Long id;
    @Size(min = 1, max = 64, message = "제목은 1~64자 이하여야 합니다.")
    private final String title;
    @Size(min = 1, max = 500, message = "내용은 1~500자 이하여야 합니다.")

    private final String contents;

    public static class Builder {

        private Long id;
        private String title;
        private String contents;

        private Builder() {}

        public Builder id(Long id) {
            this.id= id;
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

        public CardModifyRequest build() {
            return new CardModifyRequest(this);
        }
    }

    private CardModifyRequest(Long id, String title, String contents) {
        this.id = id;
        this.title = title;
        this.contents = contents;
    }

    private CardModifyRequest(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.contents = builder.contents;
    }
    
    public static Builder builder() {
        return new Builder();
    }

    public Card toCard() {
        return Card.builder()
                .id(id)
                .title(title)
                .contents(contents)
                .build();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }
}
