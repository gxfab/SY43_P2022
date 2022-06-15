package com.example.sy43;

import com.example.sy43.repositories.CategoryRepository;

import java.util.Calendar;
import java.util.Date;

public class DateManager {
    private static DateManager instance;
    private Date currentDate;
    private Date nextEndOfMonth;
     public static DateManager getInstance() {
        if (instance == null) instance = new DateManager();
        return instance;
    }
    DateManager() {
        this.currentDate = new Date();

    }
    void checkForMonth(){
    }

    public boolean isFirstDayofMonth(Calendar calendar){
        if (calendar == null) {
            throw new IllegalArgumentException("Calendar cannot be null.");
        }
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        return dayOfMonth == 1;
    }

}
