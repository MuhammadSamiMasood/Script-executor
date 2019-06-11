package com.ebricks.scriptexecutor.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Step {

    int id;
    @JsonProperty("element")
    private UIElement uiElement;
    private Event event;
    private Screen screen;

    private Map<String, Object> map;

    /*@JsonAnySetter
    public void addToMap(String property, Object value){
        if(map == null){
            map = new HashMap<String, Object>();
        }
        map.put(property, value);
    }*/

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
