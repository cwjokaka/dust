package com.dust.launcher;

import com.dust.core.stage.DefaultStage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * 继承FX的APP
 */
public class DustApplication extends Application {

    private Settings settings;

    private GameApplication app;

    private Engine engine;

    private AppWindow appWindow;

    private final static DustApplication INSTANCE = new DustApplication();

    @Override
    public void start(Stage stage) throws Exception {
        INSTANCE.engine = createEngine();
        INSTANCE.appWindow = createWindow(stage);
        INSTANCE.appWindow.show();
    }

    public static void launch(GameApplication app, Settings settings) {
        INSTANCE.app = app;
        INSTANCE.settings = settings;
        Application.launch(DustApplication.class);
    }

    private AppWindow createWindow(Stage stage) {
        return new AppWindow(
                stage,
                settings
        );
    }

    private Engine createEngine() {
        return new Engine(new DefaultStage(settings.getFps()));
    }

}
