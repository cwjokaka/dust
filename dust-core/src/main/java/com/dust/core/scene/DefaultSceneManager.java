package com.dust.core.scene;

import com.dust.core.event.loop.EventLoop;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 场景管理器
 */
public class DefaultSceneManager implements SceneManager {

    /**
     * 场景栈
     */
    private final Deque<EventLoop> sceneStack = new LinkedList<>();

    private static final DefaultSceneManager INSTANCE = new DefaultSceneManager();

    private DefaultSceneManager() {}

    public static DefaultSceneManager getInstance() {
        return INSTANCE;
    }

    public void pushScene(EventLoop eventLoop) {
        if (hasScene()) {
            currentEventLoop().pause();
        }
        sceneStack.add(eventLoop);
        eventLoop.run();
    }

    public void popScene() {
        if (hasScene()) {
            currentEventLoop().terminate();
            sceneStack.poll();
            return;
        }
        currentEventLoop().resume();
    }

    private EventLoop currentEventLoop() {
        return sceneStack.peekLast();
    }

    private boolean hasScene() {
        return !sceneStack.isEmpty();
    }

}
