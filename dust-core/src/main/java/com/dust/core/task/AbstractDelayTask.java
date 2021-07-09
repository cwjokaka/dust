package com.dust.core.task;

import com.dust.core.axis.Axis;

import java.util.Objects;

/**
 * 延时任务抽象类
 */
public abstract class AbstractDelayTask implements DelayTask, Comparable<AbstractDelayTask> {

    /**
     * 将要执行的任务
     */
    private final Task task;

    /**
     * 延时时间
     */
    protected final long delay;

    /**
     * 是否已被终止
     */
    protected boolean isTerminated = false;

    /**
     * 下次执行的时间点
     */
    protected long nextExecuteTime;

    /**
     * 已执行次数
     */
    protected long executedTimes = 0;

    /**
     * 时间轴,用于判断和计算不同维度的时间
     */
    protected final Axis axis;

    /**
     * 构造器
     * @param task 将要执行的任务
     * @param initDelay 初始的延时
     * @param delay 延时时间
     * @param axis 时间轴
     */
    public AbstractDelayTask(Task task, long initDelay, long delay, Axis axis) {
        this.task = Objects.requireNonNull(task);
        this.axis = Objects.requireNonNull(axis);
        this.delay = delay;
        this.nextExecuteTime = axis.refreshTime(initDelay);
    }

    @Override
    public void run() {
        task.run();
        executedTimes++;
    }

    @Override
    public void resetTime() {
        this.nextExecuteTime = axis.refreshTime(delay);
    }

    @Override
    public int compareTo(AbstractDelayTask o) {
        return this.nextExecuteTime > o.nextExecuteTime ? 1 : -1;
    }

    @Override
    public void terminate() {
        this.isTerminated = true;
    }

    @Override
    public boolean isTerminated() {
        return isTerminated;
    }

    @Override
    public boolean isTimeUp() {
        return axis.isTimeUp(nextExecuteTime);
    }

    @Override
    public long getExecutedTimes() {
        return executedTimes;
    }
}
