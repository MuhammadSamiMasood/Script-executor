package com.ebricks.scriptexecutor.model;

import com.ebricks.scriptexecutor.resource.MobileDriver;
import org.openqa.selenium.By;

import java.net.MalformedURLException;

public class TapEvent extends Event {

    @Override
    public void click(UIElement uiElement) throws MalformedURLException {
        MobileDriver.getInstance().getDriver().findElement(By.xpath("//*[@text='" + uiElement.getText() + "']")).click();
    }
}
