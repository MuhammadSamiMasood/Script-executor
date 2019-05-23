package com.ebricks.scriptexecutor.resource;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class MobileDriver {

    private static MobileDriver instance;

    private AndroidDriver<MobileElement> driver;
    private DesiredCapabilities desiredCapabilities;

    public AndroidDriver<MobileElement> getDriver() {
        return driver;
    }

    private MobileDriver() throws MalformedURLException {
        desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("deviceName", "Pixel 2 API 24");
        desiredCapabilities.setCapability("udid", "emulator-5554"); //DeviceId from "adb devices" command
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "7.0");
        desiredCapabilities.setCapability("skipUnlock","true");
        desiredCapabilities.setCapability("appPackage", "com.ebricks.testspot.hellowworld");
        desiredCapabilities.setCapability("appActivity","com.ebricks.testspot.hellowworld.MainActivity");
        desiredCapabilities.setCapability("noReset","false");
        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
    }

    public static MobileDriver getInstance() throws MalformedURLException {
        if (instance == null)
            instance = new MobileDriver();
        return instance;
    }
}
