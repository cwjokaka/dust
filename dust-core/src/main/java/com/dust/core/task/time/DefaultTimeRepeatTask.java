package com.dust.core.task.time;

import com.dust.core.task.param.RepeatTaskParam;

public class DefaultTimeRepeatTask extends AbstractTimeRepeatTask {

    public DefaultTimeRepeatTask(RepeatTaskParam param) {
        super(
                param.getTask(),
                param.getInitDelay() * param.getTimeEnum().getOffset(),
                param.getDelay() * param.getTimeEnum().getOffset(),
                param.getRepeatCount()
        );
    }

}
