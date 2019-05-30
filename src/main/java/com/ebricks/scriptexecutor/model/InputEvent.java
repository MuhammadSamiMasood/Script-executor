package com.ebricks.scriptexecutor.model;

import com.fasterxml.jackson.annotation.JsonGetter;

public class InputEvent extends Event {

    public InputEvent(){
        setType("input");
    }

    private double x;
    private double y;
    private String text;

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

    @JsonGetter
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public Object get(){
        return getText();
    }
}
