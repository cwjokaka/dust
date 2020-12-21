package com.dust.core.scene;

import com.dust.core.event.EventLoop;
import com.dust.core.event.NameableEventLoop;

public interface SceneManager {

    /**
     * 场景入栈
     * @param scene 场景
     */
    void pushScene(Scene scene);

    /**
     * 弹出场景
     */
    Scene popScene();

    /**
     * 跳转到某个事件循环
     * @param sceneName 场景{@link NameableEventLoop}的名称
     */
    void navigateTo(String sceneName);

}
