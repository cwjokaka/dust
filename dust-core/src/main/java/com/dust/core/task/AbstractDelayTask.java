package com.dust.core.task;

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
     * 是否被终止
     */
    protected boolean isTerminated = false;

    /**
     * 下次执行的时间点
     */
    protected long nextExecuteTime;

    public AbstractDelayTask(Task task, long delayTime) {
        this.task = Objects.requireNonNull(task);
        this.delayTime = delayTime;
        initNextExecuteTime();
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

    protected abstract void initNextExecuteTime();

}
