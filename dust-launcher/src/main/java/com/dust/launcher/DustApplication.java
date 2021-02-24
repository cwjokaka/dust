package com.dust.launcher;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 */
public class DustApplication extends Application {

    private Settings settings;

    private GameApplication app;

//    private Windows

    private final static DustApplication INSTANCE = new DustApplication();

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane border = new BorderPane();
        stage.setTitle(INSTANCE.settings.getTitle());
        stage.setScene(new Scene(border, INSTANCE.settings.getWidth(), INSTANCE.settings.getHeight()));
        stage.show();
    }

    public static void launch(GameApplication app, Settings settings) {
        INSTANCE.app = app;
        INSTANCE.settings = settings;
        Application.launch(DustApplication.class);
    }

}
