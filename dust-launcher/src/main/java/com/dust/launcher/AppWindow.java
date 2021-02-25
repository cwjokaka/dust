package com.dust.launcher;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AppWindow {

    private final Stage fxStage;

    private final Settings settings;

    public AppWindow(Stage fxStage, Settings settings) {
        this.fxStage = fxStage;
        this.settings = settings;
    }

    /**
     * 展示窗口
     */
    public void show() {
        fxStage.setScene(new Scene(new Pane(), settings.getWidth(), settings.getHeight()));
        fxStage.show();
    }

}
