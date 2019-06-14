package com.example.demo.test.java8date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTest {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");


    public static void main(String[] args){

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);

        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        System.out.println(formatLocalDate(localDate));

        LocalTime localTime = LocalTime.now();
        System.out.println(localTime);
        System.out.println(formatLocalTime(localTime));

        String destTime = "2019-05-22 12:12:11";
        LocalDateTime localDateTime1 = LocalDateTime.parse(destTime,dateTimeFormatter);
        System.out.println(localDateTime1);

        String destTime2 = "2019-05-22";
        LocalDate localDate1 = LocalDate.parse(destTime2,dateFormatter);
        System.out.println(localDate1);


        LocalDateTime localDateTime2 = LocalDateTime.of(LocalDate.now(), LocalTime.parse("11:11:12"));
        System.out.println(localDateTime2);


        System.out.println(localDateTime2.isBefore(localDateTime));

    }


    private static String formatLocalDateTime(LocalDateTime localDateTime){
        return localDateTime.format(dateTimeFormatter);
    }

    private static String formatLocalDate(LocalDate localDate){
        return localDate.format(dateFormatter);
    }

    private static String formatLocalTime(LocalTime localTime){
        return localTime.format(timeFormatter);
    }
}
