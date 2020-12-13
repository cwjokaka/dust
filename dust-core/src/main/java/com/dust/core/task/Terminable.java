package com.dust.core.task;

public interface Terminable {

    /**
     * 终止当前任务
     */
    void terminate();

    /**
     * 判断是否被终止
     */
    boolean isTerminated();

}
