package team5.todo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import team5.todo.controller.dto.CardModifyRequest;
import team5.todo.controller.dto.CardMoveRequest;
import team5.todo.controller.dto.CardSaveRequest;
import team5.todo.controller.dto.HistoryResponse;
import team5.todo.domain.Action;
import team5.todo.domain.Card;
import team5.todo.domain.History;
import team5.todo.repository.CardRepository;
import team5.todo.repository.CategoryRepository;
import team5.todo.repository.HistoryRepository;

@Service
@Transactional
public class HistoryService {

	private final HistoryRepository historyRepository;
	private final CategoryRepository categoryRepository;
	private final CardRepository cardRepository;

	public HistoryService(HistoryRepository historyRepository, CategoryRepository categoryRepository
		, CardRepository cardRepository) {
		this.historyRepository = historyRepository;
		this.categoryRepository = categoryRepository;
		this.cardRepository = cardRepository;
	}

	public List<HistoryResponse> getHistories() {
		return HistoryResponse.from(historyRepository.findAll());
	}

	public void deleteHistories() {
		historyRepository.deleteAll();
	}

	public void createHistory(CardSaveRequest cardSaveRequest) {
		historyRepository.save(History.builder()
			.title(cardSaveRequest.getTitle())
			.at(categoryRepository.findById(cardSaveRequest.getCategoryId()).getName())
			.action(Action.CREATE.getName())
			.build());
	}

	public void createHistory(CardMoveRequest cardMoveRequest) {
		Card card = cardRepository.findById(cardMoveRequest.getId());
		historyRepository.save(History.builder()
			.title(card.getTitle())
			.origin(categoryRepository.findById(card.getCategoryId()).getName())
			.destination(categoryRepository.findById(cardMoveRequest.getCategoryId()).getName())
			.action(Action.MOVE.getName())
			.build());
	}

	public void createHistory(CardModifyRequest cardModifyRequest) {
		historyRepository.save(History.builder()
			.title(cardModifyRequest.getTitle())
			.action(Action.MODIFY.getName())
			.build());
	}

	public void createHistory(long id) {
		Card card = cardRepository.findById(id);
		historyRepository.save(History.builder()
			.title(card.getTitle())
			.at(categoryRepository.findById(card.getCategoryId()).getName())
			.action(Action.DELETE.getName())
			.build());
	}

}
