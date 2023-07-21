package team5.todo.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team5.todo.controller.dto.CardResponse;
import team5.todo.controller.dto.CategoryResponse;
import team5.todo.domain.Card;
import team5.todo.domain.Category;

import team5.todo.repository.CardRepository;
import team5.todo.repository.CategoryRepository;

@Service
@Transactional
public class CategoryService {

	private final CategoryRepository categoryRepository;
	private final CardRepository cardRepository;

	public CategoryService(CategoryRepository categoryRepository, CardRepository cardRepository) {
		this.categoryRepository = categoryRepository;
		this.cardRepository = cardRepository;
	}

	public List<CategoryResponse> getAllCategories() {
		List<Category> categories = categoryRepository.findAll();
		List<Card> cards = cardRepository.findAll();
		Map<Long, List<CardResponse>> cardResponses = CardResponse.from(cards);

		return CategoryResponse.of(categories, cardResponses);
	}
}
