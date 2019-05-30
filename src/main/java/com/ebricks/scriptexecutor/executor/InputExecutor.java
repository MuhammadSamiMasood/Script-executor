package com.ebricks.scriptexecutor.executor;

import com.ebricks.scriptexecutor.finder.ElementFinder;
import com.ebricks.scriptexecutor.model.InputEvent;
import com.ebricks.scriptexecutor.model.Step;
import com.ebricks.scriptexecutor.resource.MobileDriver;
import com.ebricks.scriptexecutor.resource.TestCasesFolder;
import com.ebricks.scriptexecutor.status.Status;
import org.apache.commons.io.FileUtils;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class InputExecutor extends StepExecutor {

    public InputExecutor(Step step){
        this.step = step;
    }

    public void init() throws IOException {
        MobileDriver.getInstance().takeScreenshot();
        MobileDriver.getInstance().getDom();
    }

    public StepExecutorResponse execute() throws IOException, ParserConfigurationException, SAXException {

        InputEvent inputEvent = (InputEvent) step.getEvent();
        String domContent = FileUtils.readFileToString(new File(TestCasesFolder.getPath() + "dom/" + step.getScreen().getDom()));
        step.setUiElement(ElementFinder.findByXandYCoordinates(inputEvent.getX(), inputEvent.getY(), domContent));

        if (ElementFinder.findReplayUIElement(step.getUiElement(), MobileDriver.getInstance().getDriver().getPageSource())) {
            MobileDriver.getInstance().input(step.getUiElement(), inputEvent.getText());

            StepExecutorResponse stepExecutorResponse = new StepExecutorResponse();
            stepExecutorResponse.setId(step.getId());
            stepExecutorResponse.setUiElement(step.getUiElement());
            stepExecutorResponse.setScreen(step.getScreen());
            Status status = new Status();
            status.setStepStatus(true);
            stepExecutorResponse.setStatus(status);
            return stepExecutorResponse;
        } else {
            return null;
        }
    }
}
