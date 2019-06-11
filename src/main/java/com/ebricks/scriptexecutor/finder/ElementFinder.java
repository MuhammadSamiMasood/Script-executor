package com.ebricks.scriptexecutor.finder;

import com.ebricks.scriptexecutor.generator.ElementGenerator;
import com.ebricks.scriptexecutor.model.Step;
import com.ebricks.scriptexecutor.model.TapEvent;
import com.ebricks.scriptexecutor.model.UIElement;
import io.appium.java_client.touch.offset.PointOption;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class ElementFinder {

    public static boolean findReplayUIElement(UIElement uiElement, String pageSource) throws ParserConfigurationException, IOException, SAXException {

        if(uiElement == null){
            return true;
        }
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        StringReader stringReader = new StringReader(pageSource);
        InputSource inputSource = new InputSource(stringReader);
        Document document = documentBuilder.parse(inputSource);

        NodeList nodeList = document.getElementsByTagName("*");
        for(int i=0; i<nodeList.getLength(); i++){
            Node node = nodeList.item(i);
            if(node.getNodeName().toString().equals(uiElement.getType())){
                Element element = (Element)node;
                if(element.getAttribute("text").equals(uiElement.getText())
                && element.getAttribute("package").equals(uiElement.getPkg())
                && element.getAttribute("resource-id").equals(uiElement.getResourceId())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static UIElement findByXandYCoordinates(double x, double y, String pageSource) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        StringReader stringReader = new StringReader(pageSource);
        InputSource inputSource = new InputSource(stringReader);
        Document document = documentBuilder.parse(inputSource);

        // this list will contain all the elements in the ranges of which, the x and y coordinates fall
        List<Element> foundElements = findAllWithXandYCoordinates(x, y, document);

        // here we will find the element with smallest width and height in the found elements:
        // ------------------------------------------------------------------------------------

        // getting the width and height of first element in the list to compare with the next ones
        Element requiredElement = foundElements.get(0);
        String bounds[] = requiredElement.getAttribute("bounds").replaceAll("\\D", ",").split(",");
        int smallestWidht = Integer.parseInt(bounds[4]);
        int smallestHeight = Integer.parseInt(bounds[5]);

        for(Element element: foundElements){
            int boundWidth = Integer.parseInt(element.getAttribute("bounds").replaceAll("\\D", ",").split(",")[4]);
            int boundHeight = Integer.parseInt(element.getAttribute("bounds").replaceAll("\\D", ",").split(",")[5]);

            // checking if we found a smaller element than the previous one
            if(boundWidth <= smallestWidht && boundHeight <= smallestHeight){
                smallestWidht = boundWidth;
                smallestHeight = boundHeight;
                requiredElement = element;
            }
        }

        // now the requied element is found
        // now we need to convert it into a UIElement and return that UIElement
        return ElementGenerator.generateFromDomElement(requiredElement);
    }

    public static List<Element> findAllWithXandYCoordinates(double x, double y, Document document){
        List<Element> foundElements = new ArrayList<Element>();

        NodeList nodeList = document.getElementsByTagName("*");
        for(int i=1; i<nodeList.getLength(); i++){
            Node node = nodeList.item(i);
            Element element = (Element)node;
            String bounds = element.getAttribute("bounds");
            bounds = bounds.replaceAll("\\D", ",");
            String [] splitted = bounds.split(",");

            int boundX1 = Integer.parseInt(splitted[1]);
            int boundY1 = Integer.parseInt(splitted[2]);
            int boundX2 = Integer.parseInt(splitted[4]);
            int boundY2 = Integer.parseInt(splitted[5]);
            if(x >= boundX1 && y >= boundY1 && x <= boundX2 && y <= boundY2){
                // adding an element in the list as x and y coordinates are found to be in its range
                foundElements.add(element);
            }
        }
        return foundElements;
    }

    public static Point2D.Double relativeXandY(Step step) {
        String bounds = step.getUiElement().getBounds();
        int relativeX = (int)((TapEvent)step.getEvent()).getX() - Integer.parseInt(bounds.replaceAll("\\D", ",").split(",")[1]);
        int relativeY = (int)((TapEvent)step.getEvent()).getY() - Integer.parseInt(bounds.replaceAll("\\D", ",").split(",")[2]);

        int width = Integer.parseInt(bounds.replaceAll("\\D", ",").split(",")[4]) - Integer.parseInt(bounds.replaceAll("\\D", ",").split(",")[1]);
        int height = Integer.parseInt(bounds.replaceAll("\\D", ",").split(",")[5]) - Integer.parseInt(bounds.replaceAll("\\D", ",").split(",")[2]);

        double percetageX = ((double)relativeX / width) * 100;
        double percetageY = ((double)relativeX / width) * 100;

        return new Point2D.Double( percetageX, percetageY);
    }

    public static String findBoundsInReplayUIElement(UIElement uiElement, String pageSource) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        StringReader stringReader = new StringReader(pageSource);
        InputSource inputSource = new InputSource(stringReader);
        Document document = documentBuilder.parse(inputSource);

        NodeList nodeList = document.getElementsByTagName("*");
        for(int i=0; i<nodeList.getLength(); i++){
            Node node = nodeList.item(i);
            if(node.getNodeName().toString().equals(uiElement.getType())){
                Element element = (Element)node;
                if(element.getAttribute("text").equals(uiElement.getText())
                        && element.getAttribute("package").equals(uiElement.getPkg())
                        && element.getAttribute("resource-id").equals(uiElement.getResourceId())) {

                    return element.getAttribute("bounds");
                }
            }
        }
        return null;
    }

    public static Point findValuesFromPercentage(Point2D.Double point, String bounds){
        int width = Integer.parseInt(bounds.replaceAll("\\D", ",").split(",")[4]) - Integer.parseInt(bounds.replaceAll("\\D", ",").split(",")[1]);
        int height = Integer.parseInt(bounds.replaceAll("\\D", ",").split(",")[5]) - Integer.parseInt(bounds.replaceAll("\\D", ",").split(",")[2]);

        int relativeX = (int)(width * (point.getX()/100));
        int relativeY = (int)(height * (point.getY()/100));

        int boundX = Integer.parseInt(bounds.replaceAll("\\D", ",").split(",")[1]);
        int boundY = Integer.parseInt(bounds.replaceAll("\\D", ",").split(",")[2]);
        return new Point(boundX + relativeX , boundY + relativeY);
    }
}
