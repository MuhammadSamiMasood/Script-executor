package com.ebricks.scriptexecutor.model;

public class InputEvent extends Event {

    public InputEvent(){
        setType("input");
    }

    private String text;

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
