package com.dust.core.event.loop;

import com.dust.core.enums.TimeEnum;
import com.dust.core.task.*;
import com.dust.core.task.frame.DefaultFrameDelayTask;
import com.dust.core.task.frame.DefaultFrameRepeatTask;
import com.dust.core.task.frame.DefaultFrameScheduleTask;
import com.dust.core.task.time.DefaultTimeDelayTask;
import com.dust.core.task.time.DefaultTimeRepeatTask;
import com.dust.core.task.time.DefaultTimeScheduleTask;

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
     * 基于时间的延时任务队列
     */
    private final PriorityQueue<DelayTask> taskTimePriorityQueue = new PriorityQueue<>();

    /**
     * 基于帧的延时任务队列
     */
    private final PriorityQueue<DelayTask> taskFramePriorityQueue = new PriorityQueue<>();

    /**
     * 基于时间的就绪任务队列
     */
    private final Queue<DelayTask> taskTimeQueue = new LinkedList<>();

    /**
     * 基于帧的就绪任务队列
     */
    private final Queue<DelayTask> taskFrameQueue = new LinkedList<>();

    @Override
    public void run() {
        eventLoopThread.scheduleWithFixedDelay(this::loop, 0, 16, TimeUnit.MILLISECONDS);
    }

    @Override
    public void terminate() {
        eventLoopThread.shutdown();
    }

    public void submitTimeDelayTask(Task task, long initDelayTime, long delayTime) {
        submitTimeDelayTask(task, initDelayTime, delayTime, TimeEnum.MILLISECOND);
    }

    public void submitTimeDelayTask(Task task, long delayTime) {
        submitTimeDelayTask(task, delayTime, delayTime, TimeEnum.MILLISECOND);
    }

    public void submitTimeDelayTask(Task task, long delayTime, TimeEnum timeEnum) {
        submitTimeDelayTask(task, delayTime, delayTime, timeEnum);
    }

    public void submitTimeDelayTask(Task task, long initDelayTime, long delayTime, TimeEnum timeEnum) {
        taskTimePriorityQueue.add(new DefaultTimeDelayTask(
                task,
                initDelayTime * timeEnum.getOffset(),
                delayTime * timeEnum.getOffset()));
    }

    public void submitTimeScheduleTask(Task task, long initDelayTime, long delayTime) {
        taskTimePriorityQueue.add(new DefaultTimeScheduleTask(task, initDelayTime, delayTime));
    }

    public void submitTimeRepeatTask(Task task, long initDelayTime, long delayTime, int repeatMaxCount) {
        taskTimePriorityQueue.add(new DefaultTimeRepeatTask(task, initDelayTime, delayTime, repeatMaxCount));
    }

    public void submitFrameDelayTask(Task task, long delayTime) {
        taskFramePriorityQueue.add(new DefaultFrameDelayTask(task, delayTime, delayTime));
    }

    public void submitFrameScheduleTask(Task task, long initDelayFrame, long delayFrame) {
        taskFramePriorityQueue.add(new DefaultFrameScheduleTask(task, initDelayFrame, delayFrame));
    }

    public void submitFrameRepeatTask(Task task, long initDelayFrame, long delayFrame, int repeatMaxCount) {
        taskFramePriorityQueue.add(new DefaultFrameRepeatTask(task, initDelayFrame, delayFrame, repeatMaxCount));
    }

    @Override
    protected void runAllTasks() {
        fetchAllReadyTask(taskTimePriorityQueue, taskTimeQueue);
        fetchAllReadyTask(taskFramePriorityQueue, taskFrameQueue);
        runAllReadyTask(taskTimePriorityQueue, taskTimeQueue);
        runAllReadyTask(taskFramePriorityQueue, taskFrameQueue);
    }

    /**
     * 从任务队列获取就绪事件
     * @param from 优先队列
     * @param to 就绪队列
     */
    private void fetchAllReadyTask(PriorityQueue<DelayTask> from, Queue<DelayTask> to) {
        while (!from.isEmpty() && from.peek().isTimeUp()) {
            to.add(from.poll());
        }
    }

    /**
     * 从任务队列获取就绪事件
     * @param taskPriorityQueue 重新放回的优先队列
     * @param taskQueue 就绪队列
     */
    private void runAllReadyTask(PriorityQueue<DelayTask> taskPriorityQueue, Queue<DelayTask> taskQueue) {
        while (!taskQueue.isEmpty()) {
            DelayTask delayTask = taskQueue.poll();
            if (delayTask.isTerminated()) {
                continue;
            }
            if (!delayTask.isRunnable()) {
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
