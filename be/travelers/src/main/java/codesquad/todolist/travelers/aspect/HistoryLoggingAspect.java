package codesquad.todolist.travelers.aspect;

import codesquad.todolist.travelers.annotation.ActionId;
import codesquad.todolist.travelers.history.service.HistoryService;
import codesquad.todolist.travelers.process.service.ProcessService;
import codesquad.todolist.travelers.task.domain.dto.response.TaskPostResponseDto;
import codesquad.todolist.travelers.task.domain.entity.Task;
import codesquad.todolist.travelers.task.service.TaskService;
import java.lang.reflect.Method;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HistoryLoggingAspect {
    private final HistoryService historyService;
    private final ProcessService processService;
    private final TaskService taskService;

    public HistoryLoggingAspect(HistoryService historyService, ProcessService processService, TaskService taskService) {
        this.historyService = historyService;
        this.processService = processService;
        this.taskService = taskService;
    }

    /**
     * task 이동시 활동기록 저장을 위한 advice 입니다.
     *
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     * @Around를 사용하여 target 메서드 전후로 활동 기록 저장을 위한 데이터를 처리 합니다.
     */

    @Around("codesquad.todolist.travelers.aspect.PointCuts.updateTaskByProcessMethodInTaskService()")
    public Object logHistoryForMove(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Long taskId = getTaskIdFromArgs(proceedingJoinPoint);
        Task task = taskService.getTaskBy(taskId);
        String fromName = processService.findProcessName(task.getProcessId());

        Object result = proceedingJoinPoint.proceed();

        historyService.saveActionMoveHistory(taskId, fromName, getActionId(proceedingJoinPoint).value());

        return result;
    }

    /**
     * task 생성시 활동 기록 저장을 위한 advice 입니다.
     *
     * @param joinPoint
     * @param result
     * @return
     * @AfterReturning 을 사용하여 target 메서드의 return 값을 통해 활동 기록을 저장합니다.
     */

    @AfterReturning(value = "codesquad.todolist.travelers.aspect.PointCuts.createTaskMethodInTaskService()", returning = "result")
    public Object logHistoryForCreate(JoinPoint joinPoint, Object result) {
        TaskPostResponseDto taskPostResponseDto = (TaskPostResponseDto) result;

        historyService.saveActionCreateHistory(taskPostResponseDto.getTaskId(), getActionId(joinPoint).value());

        return taskPostResponseDto;
    }

    /**
     * task 삭제 또는 수정시 활동 기록 저장을 위한 advice입니다.
     *
     * @param joinPoint
     * @After 를 사용하여 target 메서드 종료후 활동 기록을 저장합니다.
     */

    @After("codesquad.todolist.travelers.aspect.PointCuts.updateAndDeleteMethodsInTaskService()")
    public void logHistoryForDeleteAndUpdate(JoinPoint joinPoint) {
        Long taskId = getTaskIdFromArgs(joinPoint);

        historyService.saveActionDeleteAndUpdateHistory(taskId, getActionId(joinPoint).value());
    }

    private Long getTaskIdFromArgs(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        return (Long) args[0];
    }

    private ActionId getActionId(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        ActionId actionId = method.getAnnotation(ActionId.class);
        return actionId;
    }

}
