package team5.todo.service;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import team5.todo.controller.dto.CardModifyRequest;
import team5.todo.controller.dto.CardMoveRequest;
import team5.todo.controller.dto.CardSaveRequest;
import team5.todo.repository.CardRepository;

@Service
@Transactional
public class CardService {

	private final CardRepository cardRepository;

	public CardService(CardRepository cardRepository) {
		this.cardRepository = cardRepository;
	}

	public void save(CardSaveRequest cardSaveRequest) {
		cardRepository.save(cardSaveRequest.toCard());
	}

	public void delete(final long id) {
		cardRepository.delete(id);
	}

	public void modify(CardModifyRequest cardModifyRequest) {
		cardRepository.modify(cardModifyRequest.toCard());
	}

	public void move(CardMoveRequest cardMoveRequest) {
		if (cardMoveRequest.getBeforeCardId() == null && cardMoveRequest.getAfterCardId() == null) {
			cardRepository.moveWoBothCard(cardMoveRequest);
			return;
		}
		if (cardMoveRequest.getBeforeCardId() == null) {
			cardRepository.moveWithAfterCard(cardMoveRequest);
			return;
		}
		if (cardMoveRequest.getAfterCardId() == null) {
			cardRepository.moveWithBeforeCard(cardMoveRequest);
			return;
		}
		cardRepository.moveWithBothCards(cardMoveRequest);
	}
}
