package com.ebricks.scriptexecutor.executor;

import com.ebricks.scriptexecutor.model.Step;
import com.ebricks.scriptexecutor.resource.MobileDriver;

import java.io.IOException;

public class LockExecutor extends StepExecutor {

    public LockExecutor(Step step){
        this.step = step;
    }
    public StepExecutorResponse execute() throws IOException {
        MobileDriver.getInstance().lock();

        StepExecutorResponse stepExecutorResponse = new StepExecutorResponse();
        stepExecutorResponse.setMessage("Mobile is locked");
        stepExecutorResponse.setStep(step);
        return stepExecutorResponse;
    }
}
