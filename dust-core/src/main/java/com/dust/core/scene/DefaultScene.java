package com.dust.core.scene;

public abstract class DefaultScene implements Scene {

    private final String name;

    public DefaultScene(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

}
