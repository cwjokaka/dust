package com.dust.core.task.frame;

import com.dust.core.task.Task;

public class DefaultFrameRepeatTask extends AbstractFrameRepeatTask {

    public DefaultFrameRepeatTask(Task task, long delayTime, int repeatMaxCount) {
        super(task, delayTime, repeatMaxCount);
    }

    @Override
    protected void initNextExecuteTime() {

    }

    @Override
    public boolean isTimeUp() {
        return false;
    }

}
