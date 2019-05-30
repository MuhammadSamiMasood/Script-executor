package com.ebricks.scriptexecutor.executor;

import com.ebricks.scriptexecutor.model.Screen;
import com.ebricks.scriptexecutor.model.Step;
import com.ebricks.scriptexecutor.model.UIElement;
import com.ebricks.scriptexecutor.status.Status;

public class StepExecutorResponse {

    private int id;
    private UIElement uiElement;
    private Screen screen;
    private Status status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UIElement getUiElement() {
        return uiElement;
    }

    public void setUiElement(UIElement uiElement) {
        this.uiElement = uiElement;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
