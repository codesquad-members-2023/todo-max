package codesquad.todolist.travelers.history.controller;

import codesquad.todolist.travelers.global.CommonApiResponse;
import codesquad.todolist.travelers.global.SuccessCode;
import codesquad.todolist.travelers.history.domain.dto.response.ActionHistoryResponseDto;
import codesquad.todolist.travelers.history.service.HistoryService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")

public class HistoryController {

    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }


    @Operation(summary = "활동기록 전체 삭제", description = "서버에 저장된 모든 활동기록을 삭제한다.")
    @DeleteMapping("/history")
    public ResponseEntity<CommonApiResponse> delete() {
        historyService.deleteAllHistory();

        return ResponseEntity.status(SuccessCode.HISTORY_SUCCESS.getHttpStatus())
                .body(CommonApiResponse.success(SuccessCode.HISTORY_SUCCESS.getCustomStatus(),
                        SuccessCode.HISTORY_SUCCESS.getMessage()));
    }

    @Operation(summary = "활동기록 전체 조회", description = "서버로부터 모든 활동기록을 가져온다.")
    @GetMapping("/history")
    public ResponseEntity<CommonApiResponse<?>> get() {
        List<ActionHistoryResponseDto> actionHistories = historyService.getAllHistory();

        return ResponseEntity.status(SuccessCode.HISTORY_SUCCESS.getHttpStatus())
                .body(CommonApiResponse.success(SuccessCode.HISTORY_SUCCESS.getCustomStatus(),
                        actionHistories));
    }
}
