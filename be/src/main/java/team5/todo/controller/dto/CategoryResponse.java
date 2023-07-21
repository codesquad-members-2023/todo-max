package team5.todo.controller.dto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import com.fasterxml.jackson.annotation.JsonProperty;

import team5.todo.domain.Category;

public class CategoryResponse {

	private final Long id;
	private final String name;

	@JsonProperty("cards")
	private final List<CardResponse> cardResponses;

	public static class Builder {

		private Long id;
		private String name;
		private List<CardResponse> cardResponses;

		private Builder() {}

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder cardResponses(List<CardResponse> cardResponses) {
			this.cardResponses = cardResponses;
			return this;
		}

		public CategoryResponse build() {
			return new CategoryResponse(this);
		}
	}

	private CategoryResponse(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.cardResponses = builder.cardResponses;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static List<CategoryResponse> of(List<Category> categories,
		Map<Long, List<CardResponse>> cardResponses) {
		return categories.stream()
			.map(category -> of(category, cardResponses))
			.collect(Collectors.toUnmodifiableList());
	}

	public static CategoryResponse of(Category category, Map<Long, List<CardResponse>> cardResponses) {
		return CategoryResponse.builder()
			.id(category.getId())
			.name(category.getName())
			.cardResponses(cardResponses.get(category.getId()))
			.build();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<CardResponse> getCardResponses() {
		return cardResponses;
	}
}
