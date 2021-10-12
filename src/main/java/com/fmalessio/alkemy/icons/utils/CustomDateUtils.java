package com.fmalessio.alkemy.icons.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomDateUtils {

    final static String BASIC_DATE_FORMAT = "yyyy-MM-dd";

    public static LocalDate string2LocalDate(String sDate) {
        return string2LocalDate(sDate, BASIC_DATE_FORMAT);
    }

    public static LocalDate string2LocalDate(String sDate, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDate date = LocalDate.parse(sDate, formatter);
        return date;
    }

}
