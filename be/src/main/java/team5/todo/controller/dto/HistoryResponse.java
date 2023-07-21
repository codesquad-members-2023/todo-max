package team5.todo.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import team5.todo.domain.History;

public class HistoryResponse {

	private final String title;
	private final String origin;
	private final String destination;
	private final String at;
	private final String action;
	private final LocalDateTime createdAt;

	public static class Builder {
		private String title;
		private String origin;
		private String destination;
		private String at;
		private String action;
		private LocalDateTime createdAt;

		private Builder() {
		}

		public Builder title(String title) {
			this.title = title;
			return this;
		}

		public Builder action(String action) {
			this.action = action;
			return this;
		}

		public Builder origin(String origin) {
			this.origin = origin;
			return this;
		}

		public Builder destination(String destination) {
			this.destination = destination;
			return this;
		}

		public Builder at(String at) {
			this.at = at;
			return this;
		}

		public Builder createdAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
			return this;
		}

		public HistoryResponse build() {
			return new HistoryResponse(this);
		}
	}

	public static List<HistoryResponse> from(List<History> histories) {
		return histories
			.stream()
			.map(HistoryResponse::from)
			.collect(Collectors.toUnmodifiableList());
	}

	public static HistoryResponse from(History history) {
		return HistoryResponse.builder()
			.title(history.getTitle())
			.action(history.getAction())
			.origin(history.getOrigin())
			.destination(history.getDestination())
			.at(history.getAt())
			.createdAt(history.getCreatedAt())
			.build();
	}

	private HistoryResponse(Builder builder) {
		this.title = builder.title;
		this.origin = builder.origin;
		this.destination = builder.destination;
		this.at = builder.at;
		this.action = builder.action;
		this.createdAt = builder.createdAt;
	}

	public static Builder builder() {
		return new Builder();
	}

	public String getTitle() {
		return title;
	}

	public String getOrigin() {
		return origin;
	}

	public String getDestination() {
		return destination;
	}

	public String getAt() {
		return at;
	}

	public String getAction() {
		return action;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
}
