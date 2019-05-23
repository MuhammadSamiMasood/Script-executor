package com.ebricks.scriptexecutor.Processor;

import com.ebricks.scriptexecutor.executor.ExecutorFactory;
import com.ebricks.scriptexecutor.executor.StepExecutor;
import com.ebricks.scriptexecutor.executor.StepExecutorResponse;
import com.ebricks.scriptexecutor.finder.ElementFinder;
import com.ebricks.scriptexecutor.model.Step;
import com.ebricks.scriptexecutor.resource.MobileDriver;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ebricks.scriptexecutor.model.ScriptInputData;
import com.ebricks.scriptexecutor.model.UIElement;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class ScriptProcessor {


    private ScriptInputData scriptInputData;
    private ObjectMapper objectMapper;

    public void init() throws IOException {
        objectMapper = new ObjectMapper();
        scriptInputData = objectMapper.readValue(new File("resources/UIElements.json"), ScriptInputData.class);
    }

    public void process() throws IOException, SAXException, ParserConfigurationException {

        ExecutorFactory executorFactory = new ExecutorFactory();

        String pageSource;
        for(Step step: scriptInputData.getSteps()){
            pageSource = MobileDriver.getInstance().getDriver().getPageSource();
            if(ElementFinder.find(step.getUiElement(), pageSource)){
                StepExecutor stepExecutor = executorFactory.getStepExecutor(step);
                StepExecutorResponse stepExecutorResponse = stepExecutor.execute();
                System.out.println(stepExecutorResponse.getMessage());
            }
        }
    }

    public void end() throws MalformedURLException {
        MobileDriver.getInstance().getDriver().quit();
    }




}
