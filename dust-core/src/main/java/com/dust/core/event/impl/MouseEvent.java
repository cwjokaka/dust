package com.dust.core.event.impl;

import com.dust.core.event.Event;

public class MouseEvent implements Event {

    private double mouseX;

    private double mouseY;

    public double getMouseX() {
        return mouseX;
    }

    public void setMouseX(double mouseX) {
        this.mouseX = mouseX;
    }

    public double getMouseY() {
        return mouseY;
    }

    public void setMouseY(double mouseY) {
        this.mouseY = mouseY;
    }
}
