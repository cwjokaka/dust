package com.dust.core.scene;

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
