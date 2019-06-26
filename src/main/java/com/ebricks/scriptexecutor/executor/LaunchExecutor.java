package com.ebricks.scriptexecutor.executor;

import com.ebricks.scriptexecutor.model.Step;
import com.ebricks.scriptexecutor.resource.MobileDriver;
import com.ebricks.scriptexecutor.status.Status;

import java.io.IOException;

public class LaunchExecutor extends StepExecutor {

    public LaunchExecutor(Step step){
        this.step = step;
    }

    public void init() throws IOException {
        MobileDriver.getInstance().takeScreenshot(step.getScreen());
        MobileDriver.getInstance().getDom(step.getScreen());
    }

    public StepExecutorResponse execute() throws IOException {
        MobileDriver.getInstance().launch();

        StepExecutorResponse stepExecutorResponse = new StepExecutorResponse();
        stepExecutorResponse.setId(step.getId());
        stepExecutorResponse.setUiElement(step.getUiElement());
        stepExecutorResponse.setScreen(step.getScreen());
        Status status = new Status();
        status.setStepStatus(true);
        stepExecutorResponse.setStatus(status);
        return stepExecutorResponse;
    }
}
