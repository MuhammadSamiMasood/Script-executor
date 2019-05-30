package com.ebricks.scriptexecutor.executor;

import com.ebricks.scriptexecutor.finder.ElementFinder;
import com.ebricks.scriptexecutor.model.Step;
import com.ebricks.scriptexecutor.model.TapEvent;
import com.ebricks.scriptexecutor.resource.MobileDriver;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class TapExecutor extends StepExecutor {

    TapEvent tapEvent;
    public TapExecutor(Step step) {
        this.step = step;
    }

    public void init() throws IOException {
        MobileDriver.getInstance().takeScreenshot();
        MobileDriver.getInstance().getDom();
    }

    public StepExecutorResponse execute() throws IOException, ParserConfigurationException, SAXException {

        tapEvent = (TapEvent)step.getEvent();
        step.setUiElement(ElementFinder.findByXandYCoordinates(tapEvent.getX(), tapEvent.getY(), MobileDriver.getInstance().getDriver().getPageSource()));

        MobileDriver.getInstance().click(step.getUiElement());

        StepExecutorResponse stepExecutorResponse = new StepExecutorResponse();
        stepExecutorResponse.setMessage("Element " + step.getUiElement().getText() + " is clicked");
        stepExecutorResponse.setStep(step);
        return stepExecutorResponse;
    }
}
