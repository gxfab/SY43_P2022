/*package com.example.sy43;

import com.example.sy43.db.entity.Categorydb;
import com.example.sy43.models.Category;

import java.util.List;
import java.util.stream.Collectors;

public class Utils {
    public static List<Categorydb> getTrueCategories(List<Categorydb> categories) {
        return categories.stream()

                .filter(cat -> !cat.isObjective).collect(Collectors.toList());
    }
    public static List<Categorydb> getObjectives(List<Categorydb> categories) {
        return categories.stream()
                .filter(cat -> cat.isObjective).collect(Collectors.toList());
    }
}*/
