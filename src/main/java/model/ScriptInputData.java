package model;

import model.UIElement;
import java.util.ArrayList;
import java.util.List;

public class ScriptInputData {
    private List<UIElement> uiElements = new ArrayList();

    public List<UIElement> getuiElements() {
        return uiElements;
    }

    public void setuiElements(List<UIElement> uiElements) {
        this.uiElements = uiElements;
    }
}
