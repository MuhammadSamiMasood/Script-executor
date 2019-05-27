package com.ebricks.scriptexecutor.model;

import com.fasterxml.jackson.annotation.JsonGetter;

public class InputEvent extends Event {

    public InputEvent(){
        setType("input");
    }

    private String text;

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
