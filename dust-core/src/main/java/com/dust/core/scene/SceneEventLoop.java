package com.dust.core.scene;

import com.dust.core.eventloop.impl.DustEventLoop;

public class SceneEventLoop extends DustEventLoop {

    /**
     * 当前要执行和渲染的场景
     */
    private volatile Scene currentScene;

    public SceneEventLoop(long refreshCycle) {
        super(refreshCycle);
    }

    public void switchTo(Scene scene) {
        this.currentScene = scene;
    }

    @Override
    public void executeEachTick() {
        currentScene.executeInEachFrame();
    }

    @Override
    protected void afterLoop() {
        currentScene.render();
    }

}
