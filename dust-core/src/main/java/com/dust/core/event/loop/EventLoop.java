package com.dust.core.event.loop;

/**
 * 事件循环
 */
public interface EventLoop {

    /**
     * 开始事件循环
     */
    void run();

    /**
     * 执行单次循环的内容
     */
    void loop();

    /**
     * 暂停事件循环
     */
    void pause();

    /**
     * 恢复事件循环
     */
    void resume();

    /**
     * 终止事件循环
     */
    void terminate();

    /**
     * 是否正在在执行
     */
    boolean isRunning();

}
