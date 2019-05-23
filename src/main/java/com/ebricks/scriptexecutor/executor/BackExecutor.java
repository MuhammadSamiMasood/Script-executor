package com.ebricks.scriptexecutor.executor;

import com.ebricks.scriptexecutor.model.Step;
import java.net.MalformedURLException;

public class BackExecutor extends StepExecutor {

    public BackExecutor(Step step) {
        this.step = step;
    }

    public StepExecutorResponse execute() throws MalformedURLException {
        this.step.getEvent().click(step.getUiElement());

        StepExecutorResponse stepExecutorResponse = new StepExecutorResponse();
        stepExecutorResponse.setMessage("Back button is clicked");
        stepExecutorResponse.setStep(step);
        return stepExecutorResponse;
    }
}
