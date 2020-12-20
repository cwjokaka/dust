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
     * 刷新周期(微秒)
     */
    private final long refreshCycle;

    /**
     * 执行事件循环的线程
     */
    private ScheduledExecutorService eventLoopThread = Executors.newSingleThreadScheduledExecutor();

    /**
     * 事件循环栈
     */
    private final Deque<NameableEventLoop> eventLoopStack = new LinkedList<>();

    /**
     * 事件循环Map
     */
    private final Map<String, NameableEventLoop> eventLoopMap = new HashMap<>();

    public DefaultEventLoopManager(long refreshCycle) {
        this.refreshCycle = refreshCycle;
    }

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
        eventLoopThread.shutdown();
    }

    private EventLoop currentEventLoop() {
        return eventLoopStack.peekLast();
    }

    private void runCurrentEventLoop() {
        rebuildEventLoopThread();
    }

    private void resumeCurrentEventLoop() {
        rebuildEventLoopThread();
    }

    /**
     * 重新构建事件循环线程
     */
    private void rebuildEventLoopThread() {
        eventLoopThread = Executors.newSingleThreadScheduledExecutor();
        eventLoopThread.scheduleWithFixedDelay(currentEventLoop()::loop, 0, refreshCycle, TimeUnit.MICROSECONDS);
    }


}
