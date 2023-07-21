package team5.todo.controller.dto;

import javax.validation.constraints.NotNull;

public class CardMoveRequest {

    @NotNull(message = "카드 id를 넣어주세요.")
    private final Long id;
    @NotNull(message = "카테고리 id를 넣어주세요.")
    private final Long categoryId;
    private final Long beforeCardId;
    private final Long afterCardId;

    public static class Builder {

        private Long id;
        private Long categoryId;
        private Long beforeCardId;
        private Long afterCardId;

        private Builder() {}

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder categoryId(Long categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public Builder beforeCardId(Long beforeCardId) {
            this.beforeCardId = beforeCardId;
            return this;
        }

        public Builder afterCardId(Long afterCardId) {
            this.afterCardId = afterCardId;
            return this;
        }

        public CardMoveRequest build() {
            return new CardMoveRequest(this);
        }
    }

    private CardMoveRequest(Long id, Long categoryId, Long beforeCardId, Long afterCardId) {
        this.id = id;
        this.categoryId = categoryId;
        this.beforeCardId = beforeCardId;
        this.afterCardId = afterCardId;
    }

    private CardMoveRequest(Builder builder) {
        this.id = builder.id;
        this.categoryId = builder.categoryId;
        this.beforeCardId = builder.beforeCardId;
        this.afterCardId = builder.afterCardId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public Long getBeforeCardId() {
        return beforeCardId;
    }

    public Long getAfterCardId() {
        return afterCardId;
    }
}
