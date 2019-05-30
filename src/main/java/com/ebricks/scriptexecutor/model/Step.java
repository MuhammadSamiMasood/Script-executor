package com.ebricks.scriptexecutor.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Step {

    int id;
    @JsonProperty("element")
    private UIElement uiElement;
    private Event event;
    private Screen screen;

    public UIElement getUiElement() {
        return uiElement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("element")
    public void setUiElement(UIElement uiElement) {
        this.uiElement = uiElement;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }
}
