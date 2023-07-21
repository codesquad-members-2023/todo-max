package codesquad.todolist.travelers.history.controller;


import static codesquad.todolist.travelers.global.SuccessCode.HISTORY_SUCCESS;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import codesquad.todolist.travelers.annotation.ControllerTest;
import codesquad.todolist.travelers.history.domain.dto.response.ActionHistoryResponseDto;
import codesquad.todolist.travelers.history.service.HistoryService;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@ControllerTest(HistoryController.class)
class HistoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HistoryService historyService;

    @Test
    @DisplayName("사용자의 모든 활동 기록을 삭제할수 있다.")
    void deleteSuccessTest() throws Exception {
        //given
        doNothing().when(historyService).deleteAllHistory();

        //when
        ResultActions resultActions = mockMvc.perform(delete("/api/history"));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(HISTORY_SUCCESS.getCustomStatus()))
                .andExpect(jsonPath("$.message").value(HISTORY_SUCCESS.getMessage()));
    }

    @Test
    @DisplayName("사용자의 모든 활동 기록을 가져올수 있다.")
    void getSuccessTest() throws Exception {
        //given
        given(historyService.getAllHistory()).willReturn(dummyActionHistoryResponseDtoList());

        //when
        ResultActions resultActions = mockMvc.perform(get("/api/history"));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(HISTORY_SUCCESS.getCustomStatus()))
                .andExpect(jsonPath("$.message[0].title").value("블로그에 포스팅할 것"))
                .andExpect(jsonPath("$.message[0].from").value("하고있는 일"))
                .andExpect(jsonPath("$.message[0].to").value("해야할 일"))
                .andExpect(jsonPath("$.message[0].action").value("이동"))
                .andExpect(jsonPath("$.message[0].userName").value("anonymous"))
                .andExpect(jsonPath("$.message[0].imageUrl").value("image.jpeg"));
    }

    private List<ActionHistoryResponseDto> dummyActionHistoryResponseDtoList() {
        return Arrays.asList(dummyActionHistoryResponseDtoForActionMove());
    }

    private ActionHistoryResponseDto dummyActionHistoryResponseDtoForActionMove() {
        return new ActionHistoryResponseDto("블로그에 포스팅할 것", "하고있는 일", "해야할 일"
                , "이동", LocalDateTime.now(), "anonymous", "image.jpeg");
    }
}