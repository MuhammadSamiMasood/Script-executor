package com.ebricks.scriptexecutor.resource;

import com.ebricks.scriptexecutor.config.DesiredCapabilitiesConfig;
import com.ebricks.scriptexecutor.model.UIElement;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class MobileDriver {

    private static MobileDriver instance;

    private AndroidDriver<MobileElement> driver;
    private AppiumDriverLocalService service;
    private int screenCount;

    public AndroidDriver<MobileElement> getDriver() {
        return driver;
    }

    private MobileDriver() throws IOException {

        startSession();
        createSession();
        screenCount = 0;
    }

    public static MobileDriver getInstance() throws IOException {
        if (instance == null)
            instance = new MobileDriver();
        return instance;
    }

    public String getPageSource() {
        return driver.getPageSource();
    }

    public void startSession() {
        //Build the Appium service
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.withIPAddress("127.0.0.1");
        builder.usingPort(4723);

        //Start the server with the builder
        service = AppiumDriverLocalService.buildService(builder);
        if (isServerRunnning(4723))
            service.stop();
        service.start();
    }

    public void stopSession() {
        service.stop();
    }

    public boolean isServerRunnning(int port) {

        boolean runningState = false;
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.close();
        } catch (IOException e) {
            //If control comes here, then it means that the port is in use
            runningState = true;
        } finally {
            serverSocket = null;
        }
        return runningState;
    }

    public void createSession() throws IOException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("deviceName", DesiredCapabilitiesConfig.getInstance().getDetails().get("deviceName"));
        desiredCapabilities.setCapability("udid", DesiredCapabilitiesConfig.getInstance().getDetails().get("udid"));
        desiredCapabilities.setCapability("platformName", DesiredCapabilitiesConfig.getInstance().getDetails().get("platformName"));
        desiredCapabilities.setCapability("platformVersion", DesiredCapabilitiesConfig.getInstance().getDetails().get("platformVersion"));
        desiredCapabilities.setCapability("skipUnlock", DesiredCapabilitiesConfig.getInstance().getDetails().get("skipUnlock"));
        desiredCapabilities.setCapability("appPackage", DesiredCapabilitiesConfig.getInstance().getDetails().get("appPackage"));
        desiredCapabilities.setCapability("appActivity", DesiredCapabilitiesConfig.getInstance().getDetails().get("appActivity"));
        desiredCapabilities.setCapability("noReset", DesiredCapabilitiesConfig.getInstance().getDetails().get("noReset"));
        driver = new AndroidDriver<MobileElement>(new URL(service.getUrl().toString()), desiredCapabilities);
    }


    public void quit() {
        driver.quit();
        stopSession();
    }

    public void click(UIElement uiElement) {
        driver.findElement(By.xpath("//*[@text='" + uiElement.getText() + "']")).click();
    }

    public void back() {
        driver.navigate().back();
    }

    public void lock() {
        driver.lockDevice();
    }

    public void unlock() {
        driver.unlockDevice();
    }

    public void home() {
        driver.pressKeyCode(AndroidKeyCode.HOME);
    }

    public void launch() {
        driver.launchApp();
    }

    public void input(UIElement uiElement, String text) {
        driver.findElement(By.xpath("//*[@text='" + uiElement.getText() + "']")).sendKeys(text);
    }

    public void doWait() {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    public void takeScreenshot() throws IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String filename = UUID.randomUUID().toString();
        File targetFile = new File(ResultFolder.getPath() + "/screenshots/" + screenCount + ".png");
        FileUtils.copyFile(scrFile, targetFile);
    }

    public void getDom() throws IOException {
        FileWriter fw = new FileWriter(ResultFolder.getPath() + "/dom/" + screenCount + ".xml");
        fw.write(driver.getPageSource());
        fw.close();
        screenCount += 1;
    }
}
