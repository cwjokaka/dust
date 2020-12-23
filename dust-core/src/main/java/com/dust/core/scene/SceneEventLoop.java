package com.dust.core.scene;

import com.dust.core.event.loop.DefaultEventLoop;

public class SceneEventLoop extends DefaultEventLoop {

    /**
     * 当前要渲染的场景
     */
    private Scene currentScene;

    public SceneEventLoop(long refreshCycle) {
        super(refreshCycle);
    }

    public void switchTo(Scene scene) {
        pause();
        this.currentScene = scene;
        resume();
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
