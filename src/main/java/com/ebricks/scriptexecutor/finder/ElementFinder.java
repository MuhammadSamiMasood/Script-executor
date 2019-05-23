package com.ebricks.scriptexecutor.finder;

import com.ebricks.scriptexecutor.model.UIElement;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

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

}
