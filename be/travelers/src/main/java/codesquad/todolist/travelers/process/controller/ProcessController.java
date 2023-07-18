package codesquad.todolist.travelers.process.controller;

import static codesquad.todolist.travelers.global.SuccessCode.PROCESS_SUCCESS;

import codesquad.todolist.travelers.global.CommonApiResponse;
import codesquad.todolist.travelers.process.domain.dto.ProcessRequestDto;
import codesquad.todolist.travelers.process.service.ProcessService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProcessController {

    private final ProcessService processService;

    public ProcessController(ProcessService processService) {
        this.processService = processService;
    }

    @Operation(summary = "컬럼 생성 기능", description = "name으로 컬럼 생성")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "컬럼 요청 성공!"),
            @ApiResponse(code = 400, message = "Message: 컬럼 글자 수는 1이상 50이하여야 합니다.\\n"),
            @ApiResponse(code = 503, message = "Process 생성에 실패 했습니다.")
    })
    @PostMapping("/process")
    public ResponseEntity<CommonApiResponse<?>> save(@Valid @RequestBody ProcessRequestDto processRequestDto) {
        Long processId = processService.saveProcess(processRequestDto);
        return ResponseEntity.status(PROCESS_SUCCESS.getHttpStatus())
                .body(CommonApiResponse.success(PROCESS_SUCCESS.getCustomStatus(), processId));
    }

    @Operation(summary = "컬럼 수정 기능", description = "processId와 변경할 name으로 수정")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "컬럼 요청 성공!"),
            @ApiResponse(code = 400, message = "Message: 컬럼 글자 수는 1이상 50이하여야 합니다.\\n"),
            @ApiResponse(code = 404, message = "해당 ID를 가지는 Process가 존재하지 않습니다.")
    })
    @PatchMapping("/process/{processId}")
    public ResponseEntity<CommonApiResponse<?>> update(@Valid @RequestBody ProcessRequestDto processRequestDto,
                                                       @PathVariable Long processId) {
        processService.updateProcess(processRequestDto, processId);
        return ResponseEntity.status(PROCESS_SUCCESS.getHttpStatus())
                .body(CommonApiResponse.success(PROCESS_SUCCESS.getCustomStatus(), PROCESS_SUCCESS.getMessage()));
    }

    @Operation(summary = "컬럼 삭제 기능", description = "processId로 컬럼 삭제")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "컬럼 요청 성공!"),
            @ApiResponse(code = 404, message = "해당 ID를 가지는 Process가 존재하지 않습니다.")
    })
    @DeleteMapping("/process/{processId}")
    public ResponseEntity<CommonApiResponse<?>> delete(@PathVariable Long processId) {
        processService.deleteProcess(processId);
        return ResponseEntity.status(PROCESS_SUCCESS.getHttpStatus())
                .body(CommonApiResponse.success(PROCESS_SUCCESS.getCustomStatus(), PROCESS_SUCCESS.getMessage()));
    }

}
