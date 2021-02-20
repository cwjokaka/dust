package com.dust.launcher;

import org.junit.Assert;
import org.junit.Test;

public class SettingsTest {

    @Test
    public void buildDefaultSettings() {
        Settings settings = Settings.builder().build();
        Assert.assertEquals(settings.getWidth(), 800);
        Assert.assertEquals(settings.getHeight(), 400);
    }

    @Test
    public void buildSettings() {
        Settings settings = Settings.builder()
                .width(1920)
                .height(1024)
                .build();
        Assert.assertEquals(settings.getWidth(), 1920);
        Assert.assertEquals(settings.getHeight(), 1024);
    }

}
