package com.example.nomoola.database.converter;

import androidx.room.TypeConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Converters {

    @TypeConverter
    public static LocalDate convertFromString(String value){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MMM/yyyy");
        return LocalDate.parse(value, format);
    }

    @TypeConverter
    public static String convertFromDate(LocalDate value){
        return value.format(DateTimeFormatter.ofPattern("d/MMM/yyyy"));
    }
}
