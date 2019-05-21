package model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UIElement {

    private int elementId;
    @JsonIgnore
    private int index;
    private String text;
    private String type;
    private String pkg;
    private String contentDesc;
    private boolean checkable;
    private boolean checked;
    private boolean clickable;
    private boolean enabled;
    private boolean focusable;
    private boolean focused;
    private boolean scrollable;
    private boolean longClickable;
    private boolean password;
    private boolean selected;
    private String bounds;
    private String resourceId;
    private int instance;

    public int getElementId() {
        return elementId;
    }

    public void setElementId(int elementId) {
        this.elementId = elementId;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPkg() {
        return pkg;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg;
    }

    public String getContentDesc() {
        return contentDesc;
    }

    public void setContentDesc(String contentDesc) {
        this.contentDesc = contentDesc;
    }

    public boolean isCheckable() {
        return checkable;
    }

    public void setCheckable(boolean checkable) {
        this.checkable = checkable;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isClickable() {
        return clickable;
    }

    public void setClickable(boolean clickable) {
        this.clickable = clickable;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isFocusable() {
        return focusable;
    }

    public void setFocusable(boolean focusable) {
        this.focusable = focusable;
    }

    public boolean isFocused() {
        return focused;
    }

    public void setFocused(boolean focused) {
        this.focused = focused;
    }

    public boolean isScrollable() {
        return scrollable;
    }

    public void setScrollable(boolean scrollable) {
        this.scrollable = scrollable;
    }

    public boolean isLongClickable() {
        return longClickable;
    }

    public void setLongClickable(boolean longClickable) {
        this.longClickable = longClickable;
    }

    public boolean isPassword() {
        return password;
    }

    public void setPassword(boolean password) {
        this.password = password;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getBounds() {
        return bounds;
    }

    public void setBounds(String bounds) {
        this.bounds = bounds;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public int getInstance() {
        return instance;
    }

    public void setInstance(int instance) {
        this.instance = instance;
    }

    @Override
    public String toString(){
        String string = "ElementId: " + getElementId() +
                "\nScrollable: " + isScrollable() +
                "\nLongClickable: " + isLongClickable() +
                "\nSelected: " + isSelected() +
                "\nBounds: " + getBounds();

        return string;
    }
}
