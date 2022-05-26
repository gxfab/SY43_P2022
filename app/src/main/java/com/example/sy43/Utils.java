package com.example.sy43;

import com.example.sy43.models.Category;

import java.util.List;
import java.util.stream.Collectors;

public class Utils {
    public static List<Category> getTrueCategories(List<Category> categories) {
        return categories.stream()
                .filter(cat -> !cat.isObjective).collect(Collectors.toList());
    }
    public static List<Category> getObjectives(List<Category> categories) {
        return categories.stream()
                .filter(cat -> cat.isObjective).collect(Collectors.toList());
    }

}
