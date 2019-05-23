package com.ebricks.scriptexecutor.executor;

import com.ebricks.scriptexecutor.model.Step;
import java.net.MalformedURLException;

public class TapExecutor extends StepExecutor {

    public TapExecutor(Step step) {
        this.step = step;
    }

    public StepExecutorResponse execute() throws MalformedURLException {
        this.step.getEvent().click(step.getUiElement());

        StepExecutorResponse stepExecutorResponse = new StepExecutorResponse();
        stepExecutorResponse.setMessage("Element " + step.getUiElement().getText() + " is clicked");
        stepExecutorResponse.setStep(step);
        return stepExecutorResponse;
    }
}
