package com.dust.launcher;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AppWindow {

    private final Stage fxStage;

    private final Settings settings;

    private final Scene fxScene;

    public AppWindow(Stage fxStage, Settings settings) {
        this.fxStage = fxStage;
        this.settings = settings;
        fxStage.setTitle(settings.getTitle());
        fxScene = new Scene(new Pane(), settings.getWidth(), settings.getHeight());
        fxStage.setScene(fxScene);
    }

    /**
     * 展示窗口
     */
    public void show() {
        fxStage.show();
    }

    /**
     * 关闭窗口
     */
    public void close() {
        fxStage.close();
    }
}
