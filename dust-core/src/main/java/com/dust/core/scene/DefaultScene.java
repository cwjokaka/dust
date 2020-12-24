package com.dust.core.scene;

public abstract class DefaultScene implements Scene {

    private String name;

    public DefaultScene(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

}
