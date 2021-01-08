package com.dust.launcher;


import javafx.application.Application;

public abstract class GameApplication {

    private DustApplication app;

    public void launch() {

        Application.launch(app.getClass());
    }

}
