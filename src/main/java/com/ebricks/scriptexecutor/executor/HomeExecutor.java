package com.ebricks.scriptexecutor.executor;

import com.ebricks.scriptexecutor.model.Step;
import com.ebricks.scriptexecutor.resource.MobileDriver;

import java.io.IOException;

public class HomeExecutor extends StepExecutor {

    public HomeExecutor(Step step){
        this.step = step;
    }
    public StepExecutorResponse execute() throws IOException {
        MobileDriver.getInstance().home();

        StepExecutorResponse stepExecutorResponse = new StepExecutorResponse();
        stepExecutorResponse.setMessage("Home button is clicked");
        stepExecutorResponse.setStep(step);
        return stepExecutorResponse;
    }
}
