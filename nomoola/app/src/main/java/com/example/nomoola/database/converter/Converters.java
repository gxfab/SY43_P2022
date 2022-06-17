package com.example.nomoola.database.converter;

import androidx.room.TypeConverter;

import com.example.nomoola.database.entity.Category;
import com.example.nomoola.database.entity.InOutCome;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/***
 * This class provide converters for type that are not natively supported by Sqlite.
 * String are save in sqlite but when we want to get them, the database automatically use this converter to give us the right type.
 */
public class Converters {

    /**
     * This method transforms a String into a LocalDate.
     * @param value
     * @return
     */
    @TypeConverter
    public static LocalDate convertToDateFromString(String value){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d-MM-yyyy");
        return LocalDate.parse(value, format);
    }

    /**
     * This method tansform a LocalDate into a String.
     * @param value
     * @return
     */
    @TypeConverter
    public static String convertToStringFromDate(LocalDate value){
        return value.format(DateTimeFormatter.ofPattern("d-MM-yyyy"));
    }

    /**
     * this methods convert a String into a CategoryType.
     * @param value
     * @return
     */
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

    /**
     * This methods convert a CategoryType into a String.
     * @param type
     * @return
     */
    @TypeConverter
    public static String convertToStringFromCatType(Category.CategoryType type){
        return type.toString();
    }
}
