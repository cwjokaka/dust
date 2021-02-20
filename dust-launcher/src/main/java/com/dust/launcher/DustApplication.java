package com.dust.launcher;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class DustApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane border = new BorderPane();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(border, 300, 275));
        primaryStage.show();

    }

}
