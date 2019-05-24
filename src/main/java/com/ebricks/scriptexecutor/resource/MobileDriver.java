package com.ebricks.scriptexecutor.resource;

import com.ebricks.scriptexecutor.config.DesiredCapabilitiesConfig;
import com.ebricks.scriptexecutor.model.UIElement;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class MobileDriver {

    private static MobileDriver instance;

    private AndroidDriver<MobileElement> driver;
    private DesiredCapabilities desiredCapabilities;

    public AndroidDriver<MobileElement> getDriver() {
        return driver;
    }

    private MobileDriver() throws IOException {

        desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("deviceName", DesiredCapabilitiesConfig.getInstance().getDetails().get("deviceName"));
        desiredCapabilities.setCapability("udid", DesiredCapabilitiesConfig.getInstance().getDetails().get("udid"));
        desiredCapabilities.setCapability("platformName", DesiredCapabilitiesConfig.getInstance().getDetails().get("platformName"));
        desiredCapabilities.setCapability("platformVersion", DesiredCapabilitiesConfig.getInstance().getDetails().get("platformVersion"));
        desiredCapabilities.setCapability("skipUnlock",DesiredCapabilitiesConfig.getInstance().getDetails().get("skipUnlock"));
        desiredCapabilities.setCapability("appPackage", DesiredCapabilitiesConfig.getInstance().getDetails().get("appPackage"));
        desiredCapabilities.setCapability("appActivity",DesiredCapabilitiesConfig.getInstance().getDetails().get("appActivity"));
        desiredCapabilities.setCapability("noReset",DesiredCapabilitiesConfig.getInstance().getDetails().get("noReset"));
        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
    }

    public static MobileDriver getInstance() throws IOException {
        if (instance == null)
            instance = new MobileDriver();
        return instance;
    }

    public String getPageSource(){
        return driver.getPageSource();
    }

    public void quit(){
        driver.quit();
    }

    public void click(UIElement uiElement){
        driver.findElement(By.xpath("//*[@text='" + uiElement.getText() + "']")).click();
    }

    public void back(){
        driver.navigate().back();
    }

    public void lock(){
        driver.lockDevice();
    }

    public void unlock(){
        driver.unlockDevice();
    }

    public void home(){
        driver.pressKeyCode(AndroidKeyCode.HOME);
    }

    public void launch(){
        driver.launchApp();
    }

    public void input(UIElement uiElement, String text){
        driver.findElement(By.xpath("//*[@text='" + uiElement.getText() + "']")).sendKeys(text);
    }

    public void doWait(){
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }
}
