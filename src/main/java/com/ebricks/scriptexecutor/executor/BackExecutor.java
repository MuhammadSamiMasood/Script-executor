package com.ebricks.scriptexecutor.executor;

import com.ebricks.scriptexecutor.model.Step;
import com.ebricks.scriptexecutor.resource.MobileDriver;

import java.io.IOException;
import java.net.MalformedURLException;

public class BackExecutor extends StepExecutor {

    public BackExecutor(Step step) {
        this.step = step;
    }

    public StepExecutorResponse execute() throws IOException {
        MobileDriver.getInstance().back();

        StepExecutorResponse stepExecutorResponse = new StepExecutorResponse();
        stepExecutorResponse.setMessage("Back button is clicked");
        stepExecutorResponse.setStep(step);
        return stepExecutorResponse;
    }
}
