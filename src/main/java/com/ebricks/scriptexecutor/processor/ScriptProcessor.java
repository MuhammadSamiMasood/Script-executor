package com.ebricks.scriptexecutor.processor;

import com.ebricks.scriptexecutor.config.DesiredCapabilitiesConfig;
import com.ebricks.scriptexecutor.executor.ExecutorFactory;
import com.ebricks.scriptexecutor.executor.StepExecutor;
import com.ebricks.scriptexecutor.executor.StepExecutorResponse;
import com.ebricks.scriptexecutor.finder.ElementFinder;
import com.ebricks.scriptexecutor.model.Step;
import com.ebricks.scriptexecutor.resource.MobileDriver;
import com.ebricks.scriptexecutor.resource.ResultFolder;
import com.ebricks.scriptexecutor.response.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ebricks.scriptexecutor.model.ScriptInputData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ScriptProcessor {


    private ScriptInputData scriptInputData;
    private ObjectMapper objectMapper;
    public static Logger logger = LogManager.getLogger(ScriptProcessor.class);
    Response response;

    public void init() throws IOException {

        //creating timestamped folder to store results
        String path = "resources/results/res" + (new Date().getTime());
        new File(path).mkdir();
        ResultFolder.setPath(path);

        //creating screenshots folder in timestamped folder to store screenshot of screen before every step
        new File(path + "/screenshots").mkdir();

        //creating dom folder in timestamped folder to store page source of screen before every step
        new File(path + "/dom").mkdir();

        objectMapper = new ObjectMapper();
        scriptInputData = objectMapper.readValue(new File("resources/UIElements.json"), ScriptInputData.class);
    }

    public void process() throws IOException, SAXException, ParserConfigurationException, InterruptedException {

        ExecutorFactory executorFactory = new ExecutorFactory();
        response = new Response();
        for (Step step : scriptInputData.getSteps()) {
            StepExecutor stepExecutor = executorFactory.getStepExecutor(step);
            stepExecutor.init();
            StepExecutorResponse stepExecutorResponse = stepExecutor.execute();
            response.getStepExecutorResponses().add(stepExecutorResponse);
            Thread.sleep(1000);
        }
    }


    public void end() throws IOException {
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(ResultFolder.getPath() +"/response.json"),response);
        MobileDriver.getInstance().quit();
    }

}
