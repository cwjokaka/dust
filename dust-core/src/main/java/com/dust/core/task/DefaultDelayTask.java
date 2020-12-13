package com.dust.core.task;

public class DefaultDelayTask implements DelayTask, Comparable<DefaultDelayTask> {

    private final long endTime;
    private final Task task;

    public DefaultDelayTask(Task task, long delayTime) {
        this.task = task;
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
    public int compareTo(DefaultDelayTask o) {
        return this.endTime > o.endTime ? 1 : -1;
    }

}
