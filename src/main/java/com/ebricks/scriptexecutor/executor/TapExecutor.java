package com.ebricks.scriptexecutor.executor;

import com.ebricks.scriptexecutor.finder.ElementFinder;
import com.ebricks.scriptexecutor.model.Step;
import com.ebricks.scriptexecutor.model.TapEvent;
import com.ebricks.scriptexecutor.resource.MobileDriver;
import com.ebricks.scriptexecutor.resource.TestCasesFolder;
import com.ebricks.scriptexecutor.status.Status;
import org.apache.commons.io.FileUtils;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class TapExecutor extends StepExecutor {

    public TapExecutor(Step step) {
        this.step = step;
    }

    public void init() throws IOException {
        MobileDriver.getInstance().takeScreenshot(step.getScreen());
        MobileDriver.getInstance().getDom(step.getScreen());
    }

    public StepExecutorResponse execute() throws IOException, ParserConfigurationException, SAXException {

        TapEvent tapEvent = (TapEvent)step.getEvent();
        String domContent = FileUtils.readFileToString(new File(TestCasesFolder.getPath() + "dom/" + step.getScreen().getDom()));
        step.setUiElement(ElementFinder.findByXandYCoordinates(tapEvent.getX(), tapEvent.getY(), domContent));

        if(ElementFinder.findReplayUIElement(step.getUiElement(), MobileDriver.getInstance().getDriver().getPageSource())){
            MobileDriver.getInstance().click(step);

            StepExecutorResponse stepExecutorResponse = new StepExecutorResponse();
            stepExecutorResponse.setId(step.getId());
            stepExecutorResponse.setUiElement(step.getUiElement());
            stepExecutorResponse.setScreen(step.getScreen());
            Status status = new Status();
            status.setStepStatus(true);
            stepExecutorResponse.setStatus(status);
            stepExecutorResponse.setEvent(step.getEvent());
            return stepExecutorResponse;
        }
        else{
            return null;
        }
    }
}
