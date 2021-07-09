package com.dust.core.stage;

import com.dust.core.scene.Scene;

/**
 * 舞台接口
 */
public interface Stage {

    /**
     * 开启事件循环
     */
    void start();

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
     * 跳转到某个场景
     * @param sceneName 场景的名称
     */
    void navigateTo(String sceneName);

}
