package com.dust.core.scene;

import com.dust.core.event.loop.DefaultEventLoop;

public class SceneEventLoop extends DefaultEventLoop {

    private Scene currentScene;

    public SceneEventLoop(long refreshCycle) {
        super(refreshCycle);
    }

    public void switchTo(Scene scene) {
        pause();
        this.currentScene = scene;
        run();
    }

    @Override
    public void executeEachFrame() {
        currentScene.loop();
    }

    @Override
    public void render() {
        currentScene.render();
    }

}
