package com.dust.core.task.frame;

import com.dust.core.sys.FrameSystem;
import com.dust.core.task.AbstractDelayTask;
import com.dust.core.task.Task;

/**
 * 基于帧的延时任务
 */
public abstract class AbstractFrameDelayTask extends AbstractDelayTask {

    public AbstractFrameDelayTask(Task task, long delayTime) {
        super(task, delayTime);
    }

    @Override
    public boolean isTimeUp() {
        return FrameSystem.currentFrame() >= this.nextExecuteTime;
    }

    @Override
    protected void initNextExecuteTime() {
        this.nextExecuteTime = FrameSystem.currentFrame() + delayTime;
    }

}
