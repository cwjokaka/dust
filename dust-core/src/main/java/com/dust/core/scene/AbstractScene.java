package com.dust.core.scene;

public abstract class AbstractScene implements Scene {

    private String name;

    public AbstractScene(String name) {
        this.name = name;
    }

    @Override
    public void render() {

    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void run() {

    }

    @Override
    public void loop() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void terminate() {

    }
}
