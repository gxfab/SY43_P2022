package com.example.nomoola.database.converter;

import androidx.room.TypeConverter;

import com.example.nomoola.database.entity.Category;
import com.example.nomoola.database.entity.InOutCome;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Converters {

    @TypeConverter
    public static LocalDate convertToDateFromString(String value){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d-MM-yyyy");
        return LocalDate.parse(value, format);
    }

    @TypeConverter
    public static String convertToStringFromDate(LocalDate value){
        return value.format(DateTimeFormatter.ofPattern("d-MM-yyyy"));
    }

    @TypeConverter
    public static Category.CategoryType convertToCatTypeFromString(String value){
        if(value.equals("INCOME")){
            return Category.CategoryType.INCOME;
        }else if(value.equals("OUTCOME")){
            return Category.CategoryType.OUTCOME;
        }else if(value.equals("PROJECT")){
            return Category.CategoryType.PROJECT;
        }else{
            return null;
        }
    }

    @TypeConverter
    public static String convertToStringFromCatType(Category.CategoryType type){
        return type.toString();
    }
}
