package com.ebricks.scriptexecutor.executor;

import com.ebricks.scriptexecutor.model.Step;
import com.ebricks.scriptexecutor.resource.MobileDriver;

import java.io.IOException;
import java.net.MalformedURLException;

public class TapExecutor extends StepExecutor {

    public TapExecutor(Step step) {
        this.step = step;
    }

    public void init() throws IOException {
        MobileDriver.getInstance().takeScreenshot();
        MobileDriver.getInstance().getDom();
    }

    public StepExecutorResponse execute() throws IOException {
        init();
        MobileDriver.getInstance().click(step.getUiElement());

        StepExecutorResponse stepExecutorResponse = new StepExecutorResponse();
        stepExecutorResponse.setMessage("Element " + step.getUiElement().getText() + " is clicked");
        stepExecutorResponse.setStep(step);
        return stepExecutorResponse;
    }
}
