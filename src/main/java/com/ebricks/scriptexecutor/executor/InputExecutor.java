package com.ebricks.scriptexecutor.executor;

import com.ebricks.scriptexecutor.model.InputEvent;
import com.ebricks.scriptexecutor.model.Step;
import com.ebricks.scriptexecutor.resource.MobileDriver;

import java.io.IOException;

public class InputExecutor extends StepExecutor {

    public InputExecutor(Step step){
        this.step = step;
    }

    public void init() throws IOException {
        MobileDriver.getInstance().takeScreenshot();
        MobileDriver.getInstance().getDom();
    }

    public StepExecutorResponse execute() throws IOException {
        init();
        MobileDriver.getInstance().input(step.getUiElement(), (String)step.getEvent().get());

        StepExecutorResponse stepExecutorResponse = new StepExecutorResponse();
        stepExecutorResponse.setMessage((String)step.getEvent().get() + "entered");
        stepExecutorResponse.setStep(step);
        return stepExecutorResponse;
    }
}
