package com.dust.core.event;

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
     * 从暂停{@link EventLoop#pause()}恢复事件循环
     */
    void resume();

    /**
     * 终止事件循环，并且无法使用{@link EventLoop#resume()}恢复
     */
    void terminate();

    /**
     * 是否正在运行
     */
    boolean isRunning();

    /**
     * 是否已被终止
     */
    boolean isTerminated();

}
