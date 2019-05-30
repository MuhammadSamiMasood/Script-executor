package com.ebricks.scriptexecutor.generator;

import com.ebricks.scriptexecutor.model.UIElement;
import org.w3c.dom.Element;

public class ElementGenerator {

    public static UIElement generateFromDomElement(Element element){

        UIElement uiElement = new UIElement();
        uiElement.setBounds(element.getAttribute("bounds"));
        uiElement.setCheckable(element.getAttribute("checkable") == "true");
        uiElement.setChecked(element.getAttribute("checked") == "true");
        uiElement.setClickable(element.getAttribute("clickable") == "true");
        uiElement.setContentDesc(element.getAttribute("content-desc"));
        uiElement.setElementId(0);
        uiElement.setResourceId(element.getAttribute("resource-id"));
        uiElement.setEnabled(element.getAttribute("enabled") == "true");
        uiElement.setFocusable(element.getAttribute("focusable") == "true");
        uiElement.setFocused(element.getAttribute("focused") == "true");
        uiElement.setIndex(Integer.parseInt(element.getAttribute("index")));
        uiElement.setInstance(Integer.parseInt(element.getAttribute("instance")));
        uiElement.setLongClickable(element.getAttribute("long-clickable") == "true");
        uiElement.setPassword(element.getAttribute("password") == "true");
        uiElement.setPkg(element.getAttribute("package"));
        uiElement.setScrollable(element.getAttribute("scrollable") == "true");
        uiElement.setSelected(element.getAttribute("selected") == "true");
        uiElement.setText(element.getAttribute("text"));
        uiElement.setType(element.getAttribute("class"));

        return uiElement;
    }
}
