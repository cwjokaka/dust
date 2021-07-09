package com.dust.core.task.time;

import com.dust.core.task.Task;

public class DefaultTimeScheduleTask extends AbstractTimeScheduleTask {

    public DefaultTimeScheduleTask(Task task, long initTimeDelay, long timeDelay) {
        super(
                task,
                initTimeDelay,
                timeDelay
        );
    }

}
