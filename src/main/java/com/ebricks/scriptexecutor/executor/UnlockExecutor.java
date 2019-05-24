package com.ebricks.scriptexecutor.executor;

import com.ebricks.scriptexecutor.model.Step;
import com.ebricks.scriptexecutor.resource.MobileDriver;

import java.io.IOException;

public class UnlockExecutor extends StepExecutor {

    public UnlockExecutor(Step step){
        this.step = step;
    }

    public StepExecutorResponse execute() throws IOException {
        MobileDriver.getInstance().unlock();

        StepExecutorResponse stepExecutorResponse = new StepExecutorResponse();
        stepExecutorResponse.setMessage("Mobile is unlocked");
        stepExecutorResponse.setStep(step);
        return stepExecutorResponse;
    }
}
