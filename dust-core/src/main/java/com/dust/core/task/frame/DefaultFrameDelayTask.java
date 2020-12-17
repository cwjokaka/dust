package com.dust.core.task.frame;

import com.dust.core.task.param.DelayTaskParam;

public class DefaultFrameDelayTask extends AbstractFrameDelayTask {

    public DefaultFrameDelayTask(DelayTaskParam param) {
        super(param.getTask(), param.getDelay(), param.getDelay());
    }

}
