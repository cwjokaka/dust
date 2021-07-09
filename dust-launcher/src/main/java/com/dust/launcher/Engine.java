package com.dust.launcher;

import com.dust.core.scene.Scene;
import com.dust.core.stage.Stage;

public class Engine {

    private Stage stage;

    public Engine(Stage stage) {
        this.stage = stage;
    }

    public void start() {
        stage.start();
    }

    public void startLoop(Scene scene) {
        stage.pushScene(scene);
    }

}
