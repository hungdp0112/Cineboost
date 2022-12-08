/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.helper;

import java.sql.Date;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 *
 * @author intfs
 */
public class TimeHelper {

    static DateTimeFormatter HH_MM_FORMAT = DateTimeFormatter.ofPattern("HH:mm");

    public static Time now() {
        LocalTime now = LocalTime.now();
        return Time.valueOf(now);
    }
    
    public static LocalDateTime getDateTimeNow() {
        return LocalDateTime.now();
    }
    
    public static Time getTime(LocalDateTime ldt) {
        return toSQLTime(ldt.toLocalTime());
    }
    
    public static Time toTime(Date date) {
        return toSQLTime(convertToLocalDateTime(date).toLocalTime());
    }
    
    public static LocalTime toLocalTime(java.sql.Time time) {
        return time.toLocalTime();
    }

    public static String toStringWithHourMintues(Time time) {
        return time.toLocalTime().format(HH_MM_FORMAT);
    }
    
    public static String toString(Time t) {return toString(t,false);}
    
    public static String toString(Time t, boolean withSpace) {
        if(!withSpace) return t.toString();
        return t.toString().replaceAll(":", " : ");
    };
    
    public static String toString(Date d) {return convertToLocalDateTime(d).toLocalTime().toString();}
    
    public static String toString(LocalDateTime ldt) {return getTime(ldt).toString();}
    private static LocalDateTime convertToLocalDateTime(Date d) {
        return d.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
    
    

    public static String convertMinuteToTimeFormat(Integer minutes) {
        String m = String.valueOf(minutes);
        Duration d = Duration.ofMinutes(Long.valueOf(m));
        return toStringTime(d.toHoursPart(), d.toMinutesPart(), d.toSecondsPart());
    }
    
    private static String toStringTime(int h, int m, int s) {
        return String.format("%s : %s : %s", addZero(h), addZero(m), addZero(s));
    }
    private static String addZero(long value) {
        return value < 10? ("0"+value) : String.valueOf(value);
    }

    public static String convertMinuteToTimeFormat(String minutes) {
        return convertMinuteToTimeFormat(Integer.parseInt(minutes));
    }

    public static Time add(Time start, Integer minutes) {
        if(start == null) throw new RuntimeException("Thoi gian khong the null");
        return toSQLTime(start.toLocalTime().plusMinutes(minutes));

    }
    
    public static Time minus(Time start, Integer minutes) {
        return toSQLTime(start.toLocalTime().minusMinutes(minutes));
    }

    public static Time toTime(Integer hours, Integer minutes) {
        try {
            return toSQLTime(LocalTime.of(hours, minutes));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Thời gian không hợp lệ");
        }
    }

    public static Time toTime(Integer hours, Integer minutes, Integer seconds) {
        try {
            return toSQLTime(LocalTime.of(hours, minutes, seconds));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Thoi gian khong hop le");
        }
    }

    public static Time toTime(String time) {
        String[] sp = time.split("\\s*:\\s*" );
        Integer[] ip = Arrays.stream(sp).map(Integer::parseInt).toArray(Integer[]::new);
        return TimeHelper.toTime(ip[0], ip[1], ip[2]);
    }
    
    public static Time toSQLTime(LocalTime lt) {
        return Time.valueOf(lt);
    }

  

    public static Integer compare(Time t1, Time t2) {
        if(t1 == null || t2 == null) throw new RuntimeException("Thoi gian khong the null");
        LocalTime ta = toLocalTime(t1);
        LocalTime tb = toLocalTime(t2);

        return ta.compareTo(tb);
    }
    
    public static int getHourtoString(java.sql.Time time) {
        return toLocalTime(time).getHour();
    }
    
    public static long getHourPart(java.sql.Time time){
         return toLocalTime(time).getHour();
    }
    
    public static long getMinutesPart(java.sql.Time time){
         return toLocalTime(time).getHour();
    }
    
    
    public static int getMinutetoString(java.sql.Time time) {
        return toLocalTime(time).getMinute();
    }
    
    public static void main(String[] args) {
        Time t = TimeHelper.toTime("22 : 09 :00");
        System.out.println(t.toString());

    }
}
