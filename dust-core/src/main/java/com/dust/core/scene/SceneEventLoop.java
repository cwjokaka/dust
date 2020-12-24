package com.dust.core.scene;

import com.dust.core.event.loop.DefaultEventLoop;

public class SceneEventLoop extends DefaultEventLoop {

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
    public void executeEachFrame() {
        currentScene.executeInEachFrame();
    }

    @Override
    protected void afterLoop() {
        currentScene.render();
    }

}
