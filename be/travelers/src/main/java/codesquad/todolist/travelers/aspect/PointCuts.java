package codesquad.todolist.travelers.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class PointCuts {
    @Pointcut("execution(public * codesquad.todolist.travelers.task.service.TaskService.*Task(Long,..))")
    void updateAndDeleteMethodsInTaskService() {
    }

    @Pointcut("execution(public * codesquad.todolist.travelers.task.service.TaskService.createTask(..))")
    void createTaskMethodInTaskService() {
    }

    /**
     * move pointcut
     */
    @Pointcut("execution(public * codesquad.todolist.travelers.task.service.TaskService.updateTaskByProcess(..))")
    void updateTaskByProcessMethodInTaskService() {
    }
}
