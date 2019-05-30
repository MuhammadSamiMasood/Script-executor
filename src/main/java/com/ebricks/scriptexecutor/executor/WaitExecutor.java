package com.ebricks.scriptexecutor.executor;

import com.ebricks.scriptexecutor.model.Step;
import com.ebricks.scriptexecutor.resource.MobileDriver;
import com.ebricks.scriptexecutor.status.Status;

import java.io.IOException;

public class WaitExecutor extends StepExecutor {

    public WaitExecutor(Step step){
        this.step = step;
    }

    public void init() throws IOException {
        MobileDriver.getInstance().takeScreenshot();
        MobileDriver.getInstance().getDom();
    }

    public StepExecutorResponse execute() throws IOException {
        MobileDriver.getInstance().doWait();

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
