package com.dust.core.task.time;

import com.dust.core.task.AbstractDelayTask;
import com.dust.core.task.Task;

/**
 * 基于时间的延时任务
 */
public class AbstractTimeDelayTask extends AbstractDelayTask {

    public AbstractTimeDelayTask(Task task, long delayTime) {
        super(task, delayTime);
    }

    @Override
    public boolean isTimeUp() {
        return System.currentTimeMillis() >= nextExecuteTime;
    }

    @Override
    protected void initNextExecuteTime() {
        this.nextExecuteTime = System.currentTimeMillis() + delayTime;
    }

}
