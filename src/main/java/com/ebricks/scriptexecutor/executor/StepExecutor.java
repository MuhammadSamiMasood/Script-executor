package com.ebricks.scriptexecutor.executor;

import com.ebricks.scriptexecutor.model.Step;

import java.io.IOException;
import java.net.MalformedURLException;

public abstract class StepExecutor {

    protected Step step;

    public abstract void init() throws IOException;
    public abstract StepExecutorResponse execute() throws IOException;

}
