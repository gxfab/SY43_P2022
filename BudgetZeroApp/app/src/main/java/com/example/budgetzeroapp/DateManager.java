package com.example.budgetzeroapp;

import static java.lang.Integer.parseInt;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateManager {

    public static int dateToDay(Date d){return parseInt(new SimpleDateFormat("dd", Locale.FRANCE).format(d)); }

    public static int dateToMonth(Date d){return parseInt(new SimpleDateFormat("MM", Locale.FRANCE).format(d)); }

    public static int dateToYear(Date d){return parseInt(new SimpleDateFormat("yyyy", Locale.FRANCE).format(d)); }

    public static boolean isPreviousMonth(Date d, int year, int month){
        int month2 = dateToMonth(d), year2 = dateToYear(d);
        return (month2==month+1 && year2 == year) ||
                (month2==month-11 && year2 == year+1);
    }

    public static boolean isInPreviousMonth(Date d, int year, int month, int day){
        int day2 = dateToDay(d), month2 = dateToMonth(d), year2 = dateToYear(d);
        return (month2==month && year2 == year && day2 > day) ||
                (isPreviousMonth(d, year, month) && day2 < day);
    }

    public static int previousMonthYear(int year, int month){
        if(month==12) return year;
        else return year-1;
    }

    public static int previousMonth(int month){return (month+1)%12;}

    public static boolean isLastMonthDay(int year, int month, int day){
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day);
        return day == cal.getActualMaximum(Calendar.DATE);
    }
    public static boolean isLastMonthDay(Date d){
        return isLastMonthDay(dateToYear(d), dateToMonth(d), dateToDay(d));
    }
}
