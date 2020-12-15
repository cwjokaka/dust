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
    protected final long delayTime;

    /**
     * 是否已被终止
     */
    protected boolean isTerminated = false;

    /**
     * 下次执行的时间点
     */
    protected long nextExecuteTime;

    /**
     * 时间轴,用于判断和计算不同维度的时间
     */
    protected final Axis axis;

    public AbstractDelayTask(Task task, long delayTime, Axis axis) {
        this.task = Objects.requireNonNull(task);
        this.delayTime = delayTime;
        this.axis = axis;
        this.nextExecuteTime = axis.refreshTime(delayTime);
    }

    @Override
    public void run() {
        task.run();
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

}
