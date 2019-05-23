package Processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import model.ScriptInputData;
import model.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;

public class ScriptProcessor {

    private AndroidDriver<MobileElement> driver;
    private ScriptInputData scriptInputData;
    private ObjectMapper objectMapper;

    public void init() throws IOException {
        objectMapper = new ObjectMapper();
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "Pixel 2 API 24");
        caps.setCapability("udid", "emulator-5554"); //DeviceId from "adb devices" command
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "7.0");
        caps.setCapability("skipUnlock","true");
        caps.setCapability("appPackage", "com.ebricks.testspot.hellowworld");
        caps.setCapability("appActivity","com.ebricks.testspot.hellowworld.MainActivity");
        caps.setCapability("noReset","false");
        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"),caps);
        scriptInputData = objectMapper.readValue(new File("resources/UIElements.json"), ScriptInputData.class);
    }

    public void process() throws IOException, SAXException, ParserConfigurationException {
        String pageSource = driver.getPageSource();
        for(UIElement uiElement: scriptInputData.getuiElements()){
            if(findElement(uiElement, pageSource)){
               doAction(uiElement);
            }
        }
    }

    public void end(){
        driver.quit();
    }

    public boolean findElement(UIElement uiElement, String pageSource) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        StringReader stringReader = new StringReader(pageSource);
        InputSource inputSource = new InputSource(stringReader);
        Document document = documentBuilder.parse(inputSource);

        NodeList nodeList = document.getElementsByTagName("*");
        for(int i=0; i<nodeList.getLength(); i++){
            Node node = nodeList.item(i);
            if(node.getNodeName().toString().equals(uiElement.getType())){
                //Attributes attributes = (Attributes) node.getAttributes();

                return true;
            }
        }
        return false;
    }

    public void doAction(UIElement uiElement){
        driver.findElement(By.xpath("//*[@text='" + uiElement.getText() + "']" )).click();
    }
}
