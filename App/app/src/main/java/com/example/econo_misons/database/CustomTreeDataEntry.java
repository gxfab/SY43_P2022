package com.example.econo_misons.database;

import com.anychart.chart.common.dataentry.TreeDataEntry;

public class CustomTreeDataEntry extends TreeDataEntry {
    public CustomTreeDataEntry(String id, String parent, String product, Integer value) {
        super(id, parent, value);
        setValue("product", product);
    }

    public CustomTreeDataEntry(String id, String parent, String product) {
        super(id, parent);
        setValue("product", product);
    }
}
