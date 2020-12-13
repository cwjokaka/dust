package com.dust.core.event.loop;

import com.dust.core.enums.TimeEnum;
import com.dust.core.manager.FrameManager;
import com.dust.core.task.*;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 默认事件循环
 */
public abstract class DefaultEventLoop extends AbstractEventLoop {

    /**
     * 执行事件循环的线程
     */
    private final ScheduledExecutorService eventLoopThread = Executors.newSingleThreadScheduledExecutor();

    /**
     * 延时任务队列
     */
    private final PriorityQueue<DelayTask> taskPriorityQueue = new PriorityQueue<>();

    /**
     * 就绪任务队列
     */
    private final Queue<DelayTask> taskQueue = new LinkedList<>();

    @Override
    public void run() {
        eventLoopThread.scheduleWithFixedDelay(this::loop, 0, 16, TimeUnit.MILLISECONDS);
    }

    @Override
    protected void executeLogic() {
        FrameManager.increaseFrame();
        executeEachFrame();
    }

    protected abstract void executeEachFrame();

    @Override
    public void stop() {
        eventLoopThread.shutdown();
    }

    public void submitDelayTask(Task task, long delayTime) {
        submitDelayTask(task, delayTime, TimeEnum.MILLISECOND);
    }

    public void submitDelayTask(Task task, long delayTime, TimeEnum timeEnum) {
        taskPriorityQueue.add(new DefaultDelayTask(task, delayTime * timeEnum.getOffset()));
    }

    public void submitScheduleTask(Task task, long delayTime) {
        taskPriorityQueue.add(new DefaultScheduleTask(task, delayTime));
    }

    public void submitRepeatTask(Task task, long delayTime, int repeatMaxCount) {
        taskPriorityQueue.add(new DefaultRepeatTask(task, delayTime, repeatMaxCount));
    }

    @Override
    protected void runAllTasks() {
        while (!taskPriorityQueue.isEmpty() && taskPriorityQueue.peek().isTimeUp()) {
            taskQueue.add(taskPriorityQueue.poll());
        }
        while (!taskQueue.isEmpty()) {
            DelayTask delayTask = taskQueue.poll();
            if (delayTask.isTerminated()) {
                continue;
            }
            if (!delayTask.isExecutable()) {
                taskPriorityQueue.add(delayTask);
                continue;
            }
            delayTask.run();
            if (delayTask instanceof ScheduleTask) {
                ((ScheduleTask) delayTask).refreshTime();
                taskPriorityQueue.add(delayTask);
            }
        }
    }

}
