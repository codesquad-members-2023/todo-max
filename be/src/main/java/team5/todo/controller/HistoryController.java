package team5.todo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import team5.todo.controller.dto.HistoryResponse;
import team5.todo.service.HistoryService;

@RestController
@RequestMapping("/history")
public class HistoryController {
	private final HistoryService historyService;

	public HistoryController(HistoryService historyService) {
		this.historyService = historyService;
	}

	@GetMapping()
	public List<HistoryResponse> getHistories() {
		return historyService.getHistories();
	}

	@DeleteMapping()
	public void deleteHistories() {
		historyService.deleteHistories();
	}

}
