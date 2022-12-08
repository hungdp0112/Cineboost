/**
 *
 * @author PS19229 - TRAN HOANG THUY VAN
 */
package com.org.app.util;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;


public abstract class CalendarAndClock extends TimerTask{
    public CalendarAndClock() {

    }
    public static class Clock {
        final static DateTimeFormatter format = DateTimeFormatter.ofPattern("HH':'mm':'ss");        
        public static String getTime() {
            LocalTime now = LocalTime.now();
            return now.format(format);
        }
        
        public static String getAMPM() {
            return LocalTime.now().format(DateTimeFormatter.ofPattern("a"));
        }
    }
    
    
    public static class Calendar {
        final static DateTimeFormatter format = DateTimeFormatter.ofPattern("dd'-'MM'-'yyyy");
        public static String getDate() {
            LocalDate date = LocalDate.now();
            String d = getDateOfWeek(date.getDayOfWeek().getValue() - 1)+", ";
            return d + date.format(format);
        }
        
        public static String getE(LocalDate date) {
            String d = getDateOfWeek(date.getDayOfWeek().getValue() - 1);
            return d;
        }
        
        
        private static String getDateOfWeek(int i) {
            String[] d = {"Thứ Hai","Thứ Ba","Thứ Tư", "Thứ Năm","Thứ Sáu","Thứ Bảy", "Chủ Nhật"};
            return d[i];
        }
    }
     
}
