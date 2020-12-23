package com.dust.core.scene;

import com.dust.core.event.NameableEventLoop;

/**
 * 场景接口
 */
public interface Scene {

    /**
     * 每一帧执行的内容
     */
    void executeInEachFrame();

    /**
     * 渲染
     */
    void render();

    String getName();

    void setName(String name);

}
