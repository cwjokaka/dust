package com.dust.core.event;

public class ClickData {

    private int x;

    private int y;

    public ClickData(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "ClickData{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
