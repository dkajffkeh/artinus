package com.artinus.userapp.global.utils;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {

    private static final String yyyyMMdd = "yyyyMMdd";

    public static String toyyyyMMdd(LocalDateTime localDateTime) {
        return DateTimeFormatter.ofPattern(yyyyMMdd).format(localDateTime);
    }
}
