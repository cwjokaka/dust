package com.dust.core.task.frame;

import com.dust.core.sys.FrameSystem;
import com.dust.core.task.ScheduleTask;
import com.dust.core.task.Task;

public abstract class AbstractFrameScheduleTask extends AbstractFrameDelayTask implements ScheduleTask {

    public AbstractFrameScheduleTask(Task task, long delayTime) {
        super(task, delayTime);
    }

    @Override
    public void refreshTime() {
        this.nextExecuteTime = FrameSystem.currentFrame() + this.delayTime;
    }

}
