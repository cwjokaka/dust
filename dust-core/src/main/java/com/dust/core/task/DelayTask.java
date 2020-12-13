package com.dust.core.task;

/**
 * 延时任务
 */
public interface DelayTask extends Task {

    /**
     * 判断是否到执行时间
     * @return 布尔值
     */
    boolean isTimeUp();

}
