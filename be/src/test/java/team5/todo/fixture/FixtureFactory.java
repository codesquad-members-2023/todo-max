package team5.todo.fixture;

import team5.todo.controller.dto.CardModifyRequest;
import team5.todo.controller.dto.CardMoveRequest;
import team5.todo.controller.dto.CardSaveRequest;

public class FixtureFactory {

	public static CardSaveRequest createCardSaveRequest() {
		return CardSaveRequest.builder()
			.categoryId(1L)
			.title("브루니랑 놀기")
			.contents("코쿼에서 만나기")
			.build();
	}

	public static CardModifyRequest createCardModifyRequest() {
		return CardModifyRequest.builder()
			.id(3L)
			.title("브루니랑 놀기")
			.contents("코쿼에서 밥먹기")
			.build();
	}

	public static CardMoveRequest createCardMoveRequest() {
		return CardMoveRequest.builder()
			.id(1L)
			.categoryId(2L)
			.beforeCardId(7L)
			.afterCardId(2L)
			.build();
	}
}
