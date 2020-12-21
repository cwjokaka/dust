package com.dust.core.event.manager;

import com.dust.core.event.EventLoop;
import com.dust.core.event.EventLoopManager;
import com.dust.core.event.NameableEventLoop;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 默认事件循环管理器
 */
public class DefaultEventLoopManager implements EventLoopManager {

    /**
     * 事件循环栈
     */
    private final Deque<NameableEventLoop> eventLoopStack = new LinkedList<>();

    /**
     * 事件循环Map
     */
    private final Map<String, NameableEventLoop> eventLoopMap = new HashMap<>();


    @Override
    public void pushEventLoop(NameableEventLoop eventLoop) {
        if (eventLoopMap.containsKey(eventLoop.getName())) {
            return;
        }
        pauseCurrentEventLoop();
        eventLoopMap.put(eventLoop.getName(), eventLoop);
        eventLoopStack.push(eventLoop);
        runCurrentEventLoop();
    }

    @Override
    public EventLoop popEventLoop() {
        if (eventLoopStack.isEmpty()) {
            return null;
        }
        pauseCurrentEventLoop();
        NameableEventLoop pop = eventLoopStack.pop();
        eventLoopMap.remove(pop.getName());
        resumeCurrentEventLoop();
        return pop;
    }

    @Override
    public void navigateTo(String eventLoopName) {
        if (!eventLoopMap.containsKey(eventLoopName)) {
            return;
        }
        pauseCurrentEventLoop();
        EventLoop remove = eventLoopMap.remove(eventLoopName);
        eventLoopStack.remove(remove);
        resumeCurrentEventLoop();
    }

    private void pauseCurrentEventLoop() {
        currentEventLoop().pause();
    }

    private EventLoop currentEventLoop() {
        return eventLoopStack.peekLast();
    }

    private void runCurrentEventLoop() {
        currentEventLoop().run();
    }

    private void resumeCurrentEventLoop() {
        currentEventLoop().resume();
    }

}
