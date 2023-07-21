package team5.todo.domain;

import java.time.LocalDateTime;

public class History {
	private final Long id;
	private final String action;
	private final String title;
	private final String origin;
	private final String destination;
	private final String at;
	private final LocalDateTime createdAt;

	public static class Builder {

		private Long id;
		private String action;
		private String title;
		private String origin;
		private String destination;
		private String at;
		private LocalDateTime createdAt;

		private Builder() {
		}

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder action(String action) {
			this.action = action;
			return this;
		}

		public Builder title(String title) {
			this.title = title;
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

		public History build() {
			return new History(this);
		}
	}

	private History(Builder builder) {
		this.id = builder.id;
		this.action = builder.action;
		this.title = builder.title;
		this.origin = builder.origin;
		this.destination = builder.destination;
		this.at = builder.at;
		this.createdAt = builder.createdAt;
	}

	public static Builder builder() {
		return new Builder();
	}

	public long getId() {
		return id;
	}

	public String getAction() {
		return action;
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
}
