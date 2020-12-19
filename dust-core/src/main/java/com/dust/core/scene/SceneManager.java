package com.dust.core.scene;

import com.dust.core.event.EventLoop;

public interface SceneManager {

    /**
     * 场景入栈
     * @param eventLoop
     */
    void pushScene(EventLoop eventLoop);

    /**
     * 弹出场景
     */
    void popScene();

}
