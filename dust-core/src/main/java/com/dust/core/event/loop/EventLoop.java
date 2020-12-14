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
    void stop();

    /**
     * 终止事件循环
     */
    void terminate();

}
