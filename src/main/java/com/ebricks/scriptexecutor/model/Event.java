package com.ebricks.scriptexecutor.model;

import com.fasterxml.jackson.annotation.*;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Event {

    @JsonIgnore
    private String type;

    private long eventTime;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object get() {
        return null;
    }

    public long getEventTime() {
        return eventTime;
    }

    public void setEventTime(long eventTime) {
        this.eventTime = eventTime;
    }


}
