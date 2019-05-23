package com.ebricks.scriptexecutor.model;

import com.ebricks.scriptexecutor.resource.MobileDriver;

import java.net.MalformedURLException;

public class BackEvent extends Event {

    @Override
    public void click(UIElement uiElement) throws MalformedURLException {
        MobileDriver.getInstance().getDriver().navigate().back();
    }
}
