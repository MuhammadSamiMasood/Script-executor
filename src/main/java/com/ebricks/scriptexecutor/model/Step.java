package com.ebricks.scriptexecutor.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Step {

    @JsonProperty("element")
    private UIElement uiElement;
    @JsonProperty("event")
    private Event event;

    public UIElement getUiElement() {
        return uiElement;
    }

    @JsonProperty("element")
    public void setUiElement(UIElement uiElement) {
        this.uiElement = uiElement;
    }

    public Event getEvent() {
        return event;
    }

    @JsonProperty("event")
    public void setEvent(Event event) {
        this.event = event;
    }
}
