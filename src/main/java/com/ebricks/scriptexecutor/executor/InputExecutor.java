package com.ebricks.scriptexecutor.executor;

import com.ebricks.scriptexecutor.finder.ElementFinder;
import com.ebricks.scriptexecutor.model.InputEvent;
import com.ebricks.scriptexecutor.model.Step;
import com.ebricks.scriptexecutor.resource.MobileDriver;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class InputExecutor extends StepExecutor {

    InputEvent inputEvent;
    public InputExecutor(Step step){
        this.step = step;
    }

    public void init() throws IOException {
        MobileDriver.getInstance().takeScreenshot();
        MobileDriver.getInstance().getDom();
    }

    public StepExecutorResponse execute() throws IOException, ParserConfigurationException, SAXException {

        inputEvent = (InputEvent)step.getEvent();
        step.setUiElement(ElementFinder.findByXandYCoordinates(inputEvent.getX(), inputEvent.getY(), MobileDriver.getInstance().getDriver().getPageSource()));

        MobileDriver.getInstance().input(step.getUiElement(), inputEvent.getText());

        StepExecutorResponse stepExecutorResponse = new StepExecutorResponse();
        stepExecutorResponse.setMessage((String)step.getEvent().get() + "entered");
        stepExecutorResponse.setStep(step);
        return stepExecutorResponse;
    }
}
