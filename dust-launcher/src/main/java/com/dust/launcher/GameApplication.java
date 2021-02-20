package com.dust.launcher;

import com.ls.dust.common.ReflectionUtils;
import javafx.application.Application;

public abstract class GameApplication {

//    private DustApplication app;

    public static void launch() {
        Class<? extends GameApplication> launchClass = ReflectionUtils.getCallingClass(GameApplication.class, "launch");
        GameApplication app = ReflectionUtils.newInstance(launchClass);
    }

    protected abstract void initSettings(Settings.SettingsBuilder settings);



}
