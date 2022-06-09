package com.example.sy43;

import android.graphics.drawable.Drawable;

import java.util.List;

public class DataModel {

    private List<String> nestedList;
    private List<Drawable> nestedimgList;
    private String itemText;
    private Drawable imageResource;
    private boolean isExpandable;

    public DataModel(List<String> nestedList, List<Drawable> nestedimgList, String itemText, Drawable imageResource) {
        this.nestedList = nestedList;
        this.nestedimgList = nestedimgList;
        this.itemText = itemText;
        this.imageResource = imageResource;
        this.isExpandable = false;
    }

    public List<Drawable> getNestedimgList() {
        return nestedimgList;
    }

    public Drawable getImageResource() {
        return imageResource;
    }

    public void setImageResource(Drawable imageResource) {
        this.imageResource = imageResource;
    }

    public void setExpandable(boolean expandable) {
        isExpandable = expandable;
    }

    public List<String> getNestedList() {
        return nestedList;
    }

    public String getItemText() {
        return itemText;
    }

    public boolean isExpandable() {
        return isExpandable;
    }
}