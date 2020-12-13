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
     * 延时时间(毫秒)
     */
    protected final long delayTime;

    /**
     * 是否被终止
     */
    protected boolean isTerminated = false;

    /**
     * 执行的(绝对)时间戳
     */
    protected long endTime;

    public AbstractDelayTask(Task task, long delayTime) {
        this.task = Objects.requireNonNull(task);
        this.delayTime = delayTime;
        this.endTime = System.currentTimeMillis() + delayTime;
    }

    @Override
    public boolean isTimeUp() {
        return System.currentTimeMillis() > endTime;
    }

    @Override
    public void run() {
        task.run();
    }

    @Override
    public int compareTo(AbstractDelayTask o) {
        return this.endTime > o.endTime ? 1 : -1;
    }

    @Override
    public void terminate() {
        this.isTerminated = true;
    }

    @Override
    public boolean isTerminated() {
        return isTerminated;
    }

}
