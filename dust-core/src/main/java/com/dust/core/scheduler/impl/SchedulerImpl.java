package com.dust.core.scheduler.impl;

import com.dust.core.scheduler.Scheduler;
import com.dust.core.task.DelayTask;
import com.dust.core.task.Task;
import com.dust.core.task.TaskRef;
import com.dust.core.task.tick.DefaultTickDelayTask;
import com.dust.core.task.tick.DefaultTickRepeatTask;
import com.dust.core.task.tick.DefaultTickScheduleTask;
import com.dust.core.task.time.DefaultTimeDelayTask;
import com.dust.core.task.time.DefaultTimeRepeatTask;
import com.dust.core.task.time.DefaultTimeScheduleTask;

import java.util.LinkedList;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;

public class SchedulerImpl implements Scheduler {

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
    public TaskRef setTimeDelay(Task task, long timeDelay) {
        Objects.requireNonNull(task);
        DefaultTimeDelayTask delayTask = new DefaultTimeDelayTask(task, timeDelay);
        taskTimePriorityQueue.add(delayTask);
        return delayTask;
    }

    @Override
    public TaskRef setTimeRepeat(Task task, int repeatCount, long timeDelay) {
        return setTimeRepeat(task, repeatCount, timeDelay, timeDelay);
    }

    @Override
    public TaskRef setTimeRepeat(Task task, int repeatCount, long initTimeDelay, long timeDelay) {
        Objects.requireNonNull(task);
        DefaultTimeRepeatTask timeRepeatTask = new DefaultTimeRepeatTask(task, repeatCount, initTimeDelay, timeDelay);
        taskTimePriorityQueue.add(timeRepeatTask);
        return timeRepeatTask;
    }

    @Override
    public TaskRef setTimeInterval(Task task, long timeDelay) {
        return setTimeInterval(task, timeDelay, timeDelay);
    }

    @Override
    public TaskRef setTimeInterval(Task task, long initTimeDelay, long timeDelay) {
        Objects.requireNonNull(task);
        DefaultTimeScheduleTask timeScheduleTask = new DefaultTimeScheduleTask(task, initTimeDelay, timeDelay);
        taskTimePriorityQueue.add(timeScheduleTask);
        return timeScheduleTask;
    }

    @Override
    public TaskRef setLoopDelay(Task task, long loopDelay) {
        Objects.requireNonNull(task);
        DefaultTickDelayTask frameDelayTask = new DefaultTickDelayTask(task, loopDelay);
        taskFramePriorityQueue.add(frameDelayTask);
        return frameDelayTask;
    }

    @Override
    public TaskRef setLoopRepeat(Task task, long loopDelay, int repeatCount) {
        return setLoopRepeat(task, loopDelay, loopDelay, repeatCount);
    }

    @Override
    public TaskRef setLoopRepeat(Task task, long initLoopDelay, long loopDelay, int repeatCount) {
        Objects.requireNonNull(task);
        DefaultTickRepeatTask frameRepeatTask = new DefaultTickRepeatTask(task, initLoopDelay, loopDelay, repeatCount);
        taskFramePriorityQueue.add(frameRepeatTask);
        return frameRepeatTask;
    }

    @Override
    public TaskRef setLoopInterval(Task task, long loopDelay) {
        return setLoopInterval(task, loopDelay, loopDelay);
    }

    @Override
    public TaskRef setLoopInterval(Task task, long initLoopDelay, long loopDelay) {
        Objects.requireNonNull(task);
        DefaultTickScheduleTask frameScheduleTask = new DefaultTickScheduleTask(task, initLoopDelay, loopDelay);
        taskFramePriorityQueue.add(frameScheduleTask);
        return frameScheduleTask;
    }

    @Override
    public void runAllTasks() {
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
            if (!delayTask.runnable()) {
                taskPriorityQueue.add(delayTask);
                continue;
            }
            delayTask.run();
            if (delayTask.repeatable()) {
                delayTask.resetTime();
                taskPriorityQueue.add(delayTask);
            }
        }
    }

}
