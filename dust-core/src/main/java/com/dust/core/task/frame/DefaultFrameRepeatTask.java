package com.dust.core.task.frame;

import com.dust.core.task.param.RepeatTaskParam;

public class DefaultFrameRepeatTask extends AbstractFrameRepeatTask {

    public DefaultFrameRepeatTask(RepeatTaskParam param) {
        super(param.getTask(), param.getInitDelay(), param.getDelay(), param.getRepeatCount());
    }

    @Override
    public boolean isTimeUp() {
        return false;
    }

}
