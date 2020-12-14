package com.dust.core.task.time;

import com.dust.core.task.ScheduleTask;
import com.dust.core.task.Task;

public abstract class AbstractTimeScheduleTask extends AbstractTimeDelayTask implements ScheduleTask {

    public AbstractTimeScheduleTask(Task task, long delayTime) {
        super(task, delayTime);
    }

    @Override
    public void refreshTime() {
        this.nextExecuteTime = System.currentTimeMillis() + this.delayTime;
    }

}
