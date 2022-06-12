package com.example.sy43;

import android.graphics.drawable.Drawable;
import android.widget.EditText;

import androidx.cardview.widget.CardView;

import java.util.List;

public class DataModel {

    private List<String> nestedList;
    private List<Drawable> nestedimgList;
    private List<CardView> btnList;
    private List<EditText> etList;
    private String itemText;
    private Drawable imageResource;
    private boolean isExpandable;

    public DataModel(List<String> nestedList, List<Drawable> nestedimgList, List<CardView> btnList, List<EditText> etList,String itemText, Drawable imageResource) {
        this.nestedList = nestedList;
        this.nestedimgList = nestedimgList;
        this.itemText = itemText;
        this.imageResource = imageResource;
        this.isExpandable = false;
        this.btnList = btnList;
        this.etList = etList;
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

    public List<CardView> getBtnList() {
        return btnList;
    }

    public List<EditText> getEtList() {
        return etList;
    }
}