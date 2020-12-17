package com.dust.core.task.time;

import com.dust.core.task.param.ScheduleTaskParam;

public class DefaultTimeScheduleTask extends AbstractTimeScheduleTask {

    public DefaultTimeScheduleTask(ScheduleTaskParam param) {
        super(
                param.getTask(),
                param.getInitDelay() * param.getTimeEnum().getOffset(),
                param.getDelay() * param.getTimeEnum().getOffset()
        );
    }

}
