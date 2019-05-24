package com.ebricks.scriptexecutor.executor;

import com.ebricks.scriptexecutor.model.Step;
import com.ebricks.scriptexecutor.resource.MobileDriver;

import java.io.IOException;

public class LaunchExecutor extends StepExecutor {

    public LaunchExecutor(Step step){
        this.step = step;
    }
    public StepExecutorResponse execute() throws IOException {
        MobileDriver.getInstance().launch();

        StepExecutorResponse stepExecutorResponse = new StepExecutorResponse();
        stepExecutorResponse.setMessage("App is lauched");
        stepExecutorResponse.setStep(step);
        return stepExecutorResponse;
    }
}
