package com.ebricks.scriptexecutor.executor;

import com.ebricks.scriptexecutor.model.Event;
import com.ebricks.scriptexecutor.model.Step;

public class StepExecutorResponse {
    private String message;
    private Step step;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setStep(Step step) {
        this.step = step;
    }

    public Step getStep() {
        return step;
    }
}
