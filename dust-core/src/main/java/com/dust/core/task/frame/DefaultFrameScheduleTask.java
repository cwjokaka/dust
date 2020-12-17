package com.dust.core.task.frame;

import com.dust.core.task.param.ScheduleTaskParam;

public class DefaultFrameScheduleTask extends AbstractFrameScheduleTask {

    public DefaultFrameScheduleTask(ScheduleTaskParam param) {
        super(param.getTask(), param.getInitDelay(), param.getDelay());
    }

}
