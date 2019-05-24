package com.ebricks.scriptexecutor.model;

import com.ebricks.scriptexecutor.resource.MobileDriver;

import java.net.MalformedURLException;

public class TapEvent extends Event {

    public TapEvent(){
        setType("tap");
    }

    private double x;
    private double y;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

}
