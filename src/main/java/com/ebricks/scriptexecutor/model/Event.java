package com.ebricks.scriptexecutor.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = BackEvent.class, name = "back"),
        @JsonSubTypes.Type(value = TapEvent.class, name = "tap"),
        @JsonSubTypes.Type(value = LockEvent.class, name = "lock"),
        @JsonSubTypes.Type(value = UnlockEvent.class, name = "unlock"),
        @JsonSubTypes.Type(value = HomeEvent.class, name = "home"),
        @JsonSubTypes.Type(value = LaunchEvent.class, name = "launch"),
        @JsonSubTypes.Type(value = InputEvent.class, name = "input"),
        @JsonSubTypes.Type(value = WaitEvent.class, name = "wait")
})
public abstract class Event{

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object get(){
        return null;
    }

}
