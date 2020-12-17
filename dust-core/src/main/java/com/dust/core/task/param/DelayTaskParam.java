package com.dust.core.task.param;

import com.dust.core.enums.TimeEnum;

public interface DelayTaskParam extends TaskParam {

    long getDelay();

    TimeEnum getTimeEnum();

}
