package com.dust.core.event.loop;

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
     * 停止事件循环
     */
    void stop();

}
