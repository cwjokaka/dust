package com.dust.core.event.loop;

import com.dust.core.manager.FrameManager;
import com.dust.core.task.*;

import java.util.PriorityQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 默认事件循环
 */
public abstract class DefaultEventLoop extends AbstractEventLoop {

    /**
     * 执行循环的线程
     */
    private final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    /**
     * 延时任务队列
     */
    private final PriorityQueue<DelayTask> taskPriorityQueue = new PriorityQueue<>();

    public void run() {
        scheduledExecutorService.scheduleWithFixedDelay(this::loop, 0, 16, TimeUnit.MILLISECONDS);
    }

    @Override
    protected void executeLogic() {
        FrameManager.increaseFrame();
        executeEachFrame();
    }

    protected abstract void executeEachFrame();

    @Override
    public void stop() {
        scheduledExecutorService.shutdown();
    }

    public void submitDelayTask(Task task, long delay) {
        taskPriorityQueue.add(new DefaultDelayTask(task, delay));
    }

    public void submitScheduleTask(Task task, long delay) {
        taskPriorityQueue.add(new DefaultScheduleTask(task, delay));
    }

    @Override
    protected void runAllTasks() {
        while (taskPriorityQueue.peek() != null && taskPriorityQueue.peek().isTimeUp()) {
            DelayTask delayTask = taskPriorityQueue.poll();
            delayTask.run();
            if (delayTask instanceof ScheduleTask) {
                ((ScheduleTask) delayTask).refreshTime();
                taskPriorityQueue.add(delayTask);
            }
        }
    }

}
