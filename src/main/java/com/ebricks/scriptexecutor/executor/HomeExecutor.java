package com.ebricks.scriptexecutor.executor;

import com.ebricks.scriptexecutor.model.Step;
import com.ebricks.scriptexecutor.resource.MobileDriver;

import java.io.IOException;

public class HomeExecutor extends StepExecutor {

    public HomeExecutor(Step step){
        this.step = step;
    }

    public void init() throws IOException {
        MobileDriver.getInstance().takeScreenshot();
        MobileDriver.getInstance().getDom();
    }

    public StepExecutorResponse execute() throws IOException {
        init();
        MobileDriver.getInstance().home();

        StepExecutorResponse stepExecutorResponse = new StepExecutorResponse();
        stepExecutorResponse.setMessage("Home button is clicked");
        stepExecutorResponse.setStep(step);
        return stepExecutorResponse;
    }
}
