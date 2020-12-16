package com.dust.core.launcher;

import com.dust.core.event.loop.DefaultEventLoop;
import com.dust.core.event.loop.EventLoop;

/**
 * 启动器
 */
public class DustLauncher {

    private final DefaultEventLoop eventLoop;

    public DustLauncher(DefaultEventLoop eventLoop) {
        this.eventLoop = eventLoop;
    }

    public void start() {
        eventLoop.run();
    }

    public EventLoop getEventLoop() {
        return eventLoop;
    }

}
