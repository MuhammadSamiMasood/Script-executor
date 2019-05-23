package com.ebricks.scriptexecutor.executor;

import com.ebricks.scriptexecutor.model.Step;

public class ExecutorFactory {

    public StepExecutor getStepExecutor(Step step) {
        if (step.getEvent().getClass().getName().equalsIgnoreCase("com.ebricks.scriptexecutor.model.BackEvent")) {
            return new BackExecutor(step);
        }
        else{
            return new TapExecutor(step);
        }
    }
}
