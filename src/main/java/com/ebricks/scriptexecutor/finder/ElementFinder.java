package com.ebricks.scriptexecutor.finder;

import com.ebricks.scriptexecutor.model.UIElement;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class ElementFinder {

    public static boolean find(UIElement uiElement, String pageSource) throws ParserConfigurationException, IOException, SAXException {

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
                //Attributes attributes = (Attributes) node.getAttributes();

                return true;
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
        List<Element> foundElements = new ArrayList<Element>();


        NodeList nodeList = document.getElementsByTagName("*");
        for(int i=1; i<nodeList.getLength(); i++){
            Node node = nodeList.item(i);

            Element element = (Element)node;
            String bounds = element.getAttribute("bounds");
            bounds = bounds.replaceAll("\\D", ",");
            String [] splitted = bounds.split(",");

            int boundX = Integer.parseInt(splitted[1]);
            int boundY = Integer.parseInt(splitted[2]);
            int boundWidth = Integer.parseInt(splitted[4]);
            int boundHeight = Integer.parseInt(splitted[5]);
            if(x >= boundX && y >= boundY && x < boundWidth && y < boundHeight){

                // adding an element in the list as x and y coordinates are found to be in its range
                foundElements.add(element);
            }

        }

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

        UIElement uiElement = new UIElement();
        uiElement.setBounds(requiredElement.getAttribute("bounds"));
        uiElement.setCheckable(requiredElement.getAttribute("checkable") == "true");
        uiElement.setChecked(requiredElement.getAttribute("checked") == "true");
        uiElement.setClickable(requiredElement.getAttribute("clickable") == "true");
        uiElement.setContentDesc(requiredElement.getAttribute("content-desc"));
        uiElement.setElementId(0);
        uiElement.setResourceId(requiredElement.getAttribute("resource-id"));
        uiElement.setEnabled(requiredElement.getAttribute("enabled") == "true");
        uiElement.setFocusable(requiredElement.getAttribute("focusable") == "true");
        uiElement.setFocused(requiredElement.getAttribute("focused") == "true");
        uiElement.setIndex(Integer.parseInt(requiredElement.getAttribute("index")));
        uiElement.setInstance(Integer.parseInt(requiredElement.getAttribute("instance")));
        uiElement.setLongClickable(requiredElement.getAttribute("long-clickable") == "true");
        uiElement.setPassword(requiredElement.getAttribute("password") == "true");
        uiElement.setPkg(requiredElement.getAttribute("package"));
        uiElement.setScrollable(requiredElement.getAttribute("scrollable") == "true");
        uiElement.setSelected(requiredElement.getAttribute("selected") == "true");
        uiElement.setText(requiredElement.getAttribute("text"));
        uiElement.setType(requiredElement.getAttribute("class"));

        return uiElement;
    }
}
