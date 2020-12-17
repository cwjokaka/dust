package com.dust.core.event.loop;

import com.dust.core.enums.TimeEnum;
import com.dust.core.task.*;
import com.dust.core.task.frame.DefaultFrameDelayTask;
import com.dust.core.task.frame.DefaultFrameRepeatTask;
import com.dust.core.task.frame.DefaultFrameScheduleTask;
import com.dust.core.task.param.DelayTaskParam;
import com.dust.core.task.param.RepeatTaskParam;
import com.dust.core.task.param.ScheduleTaskParam;
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

    public DelayTask submitTimeDelayTask(DelayTaskParam param) {
        DefaultTimeDelayTask task = new DefaultTimeDelayTask(param);
        taskTimePriorityQueue.add(task);
        return task;
    }

    public ScheduleTask submitTimeScheduleTask(ScheduleTaskParam param) {
        DefaultTimeScheduleTask task = new DefaultTimeScheduleTask(param);
        taskTimePriorityQueue.add(task);
        return task;
    }

    public RepeatTask submitTimeRepeatTask(RepeatTaskParam param) {
        DefaultTimeRepeatTask task = new DefaultTimeRepeatTask(param);
        taskTimePriorityQueue.add(task);
        return task;
    }

    public DelayTask submitFrameDelayTask(DelayTaskParam param) {
        DefaultFrameDelayTask task = new DefaultFrameDelayTask(param);
        taskFramePriorityQueue.add(task);
        return task;
    }

    public ScheduleTask submitFrameScheduleTask(ScheduleTaskParam param) {
        DefaultFrameScheduleTask task = new DefaultFrameScheduleTask(param);
        taskFramePriorityQueue.add(task);
        return task;
    }

    public RepeatTask submitFrameRepeatTask(RepeatTaskParam param) {
        DefaultFrameRepeatTask task = new DefaultFrameRepeatTask(param);
        taskFramePriorityQueue.add(task);
        return task;
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
