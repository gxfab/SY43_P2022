package com.example.nomoola.database.converter;

import androidx.room.TypeConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Converters {

    @TypeConverter
    public static LocalDate convertFromString(String value){
        return LocalDate.parse(value);
    }

    @TypeConverter
    public static String convertFromDate(LocalDate value){
        return value.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
