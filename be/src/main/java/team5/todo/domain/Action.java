package team5.todo.domain;

public enum Action {

	CREATE("생성"),
	DELETE("삭제"),
	MODIFY("수정"),
	MOVE("이동");

	private final String name;

	Action(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
