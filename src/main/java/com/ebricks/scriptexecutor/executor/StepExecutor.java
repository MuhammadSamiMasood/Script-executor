package com.ebricks.scriptexecutor.executor;

import com.ebricks.scriptexecutor.model.Step;

import java.net.MalformedURLException;

public abstract class StepExecutor {

    protected Step step;

    public abstract StepExecutorResponse execute() throws MalformedURLException;

}
