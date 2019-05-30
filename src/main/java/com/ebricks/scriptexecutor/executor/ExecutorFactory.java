package com.ebricks.scriptexecutor.executor;

import com.ebricks.scriptexecutor.model.Step;

public class ExecutorFactory {

    public StepExecutor getStepExecutor(Step step) {
        if (step.getEvent().getType() == "back") {
            return new BackExecutor(step);
        } else if (step.getEvent().getType() == "lock") {
            return new LockExecutor(step);
        } else if (step.getEvent().getType() == "unlock") {
            return new UnlockExecutor(step);
        } else if (step.getEvent().getType() == "home") {
            return new HomeExecutor(step);
        } else if (step.getEvent().getType() == "launch") {
            return new LaunchExecutor(step);
        } else if (step.getEvent().getType() == "input") {
            return new InputExecutor(step);
        } else if (step.getEvent().getType() == "wait") {
            return new WaitExecutor(step);
        } else {
            return new TapExecutor(step);
        }
    }
}
