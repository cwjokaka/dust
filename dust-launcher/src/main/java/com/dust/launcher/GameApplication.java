package com.dust.launcher;

import com.ls.dust.common.ReflectionUtils;

public abstract class GameApplication {

    public static void launch() {
        Class<? extends GameApplication> launchClass = ReflectionUtils.getCallingClass(GameApplication.class, "launch");
        GameApplication app = ReflectionUtils.newInstance(launchClass);
        DustApplication.launch(app, app.getSettings());
    }

    protected abstract void initSettings(Settings.SettingsBuilder builder);

    private Settings getSettings() {
        Settings.SettingsBuilder settingsBuilder = Settings.builder();
        initSettings(settingsBuilder);
        return settingsBuilder.build();
    }

}
