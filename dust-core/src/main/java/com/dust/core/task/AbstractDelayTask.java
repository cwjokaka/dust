package com.dust.core.task;

import java.util.Objects;

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

}
