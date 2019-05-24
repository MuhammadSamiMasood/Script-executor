package com.ebricks.scriptexecutor.processor;

import com.ebricks.scriptexecutor.config.DesiredCapabilitiesConfig;
import com.ebricks.scriptexecutor.executor.ExecutorFactory;
import com.ebricks.scriptexecutor.executor.StepExecutor;
import com.ebricks.scriptexecutor.executor.StepExecutorResponse;
import com.ebricks.scriptexecutor.finder.ElementFinder;
import com.ebricks.scriptexecutor.model.Step;
import com.ebricks.scriptexecutor.resource.MobileDriver;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ebricks.scriptexecutor.model.ScriptInputData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class ScriptProcessor {


    private ScriptInputData scriptInputData;
    private ObjectMapper objectMapper;
    public static Logger logger = LogManager.getLogger(ScriptProcessor.class);

    public void init() throws IOException {
        objectMapper = new ObjectMapper();
        scriptInputData = objectMapper.readValue(new File("resources/UIElements.json"), ScriptInputData.class);
    }

    public void process() throws IOException, SAXException, ParserConfigurationException, InterruptedException {

        ExecutorFactory executorFactory = new ExecutorFactory();

        for(Step step: scriptInputData.getSteps()){
            String pageSource = MobileDriver.getInstance().getPageSource();
            if(ElementFinder.find(step.getUiElement(), pageSource)){
                StepExecutor stepExecutor = executorFactory.getStepExecutor(step);
                StepExecutorResponse stepExecutorResponse = stepExecutor.execute();
                logger.info(stepExecutorResponse.getMessage());
                Thread.sleep(1000);
            }
        }
    }

    public void end() throws IOException {
        MobileDriver.getInstance().quit();
    }

}
