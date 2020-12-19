package com.dust.core.event;

public interface EventLoopManager {

    /**
     * 入栈事件循环
     * @param eventLoop 事件循环
     */
    void pushEventLoop(NameableEventLoop eventLoop);

    /**
     * 出栈事件循环
     * @return 刚出栈的事件循环
     */
    EventLoop popEventLoop();

    /**
     * 跳转到某个事件循环
     * @param eventLoopName 事件循环{@link NameableEventLoop}的名称
     */
    void navigateTo(String eventLoopName);

}
