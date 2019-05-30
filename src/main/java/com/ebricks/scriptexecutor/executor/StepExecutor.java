package com.ebricks.scriptexecutor.executor;

import com.ebricks.scriptexecutor.model.Step;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public abstract class StepExecutor {

    protected Step step;

    public abstract void init() throws IOException;
    public abstract StepExecutorResponse execute() throws IOException, ParserConfigurationException, SAXException;

}
