package com.example.basic;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class TimeComparison {
 public static void main(String[] args) {   
   Date date = new Date(); // java.util.Date (UTC 기반)

   SimpleDateFormat localFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   localFormat.setTimeZone(TimeZone.getDefault()); // JVM 기본 타임존 (예: Asia/Seoul)
   System.out.println("Date - JVM 시간대 기준: " + localFormat.format(date));

   SimpleDateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
   System.out.println("Date - UTC 기준: " + utcFormat.format(date));


   System.out.println();
   
   LocalDateTime localDateTime = LocalDateTime.now(); // LocalDateTime (타임존 없음)
   System.out.println(
       "LocalDateTime: " + localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
 }
}
