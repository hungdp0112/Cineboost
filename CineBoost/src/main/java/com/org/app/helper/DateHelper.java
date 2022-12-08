package com.org.app.helper;

import com.org.app.message.ConfirmDialog;
import java.util.Date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import javax.swing.JFrame;


public class DateHelper {
    static SimpleDateFormat formater = new SimpleDateFormat();
    
    public static DateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
    public static DateFormat DATE_SLASH_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    public static DateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    
    
    public static DateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");
    public static DateFormat DATETIME_FORMAT = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    public static DateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
    
    public static DateTimeFormatter DATE_FROMATER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static DateTimeFormatter DATE_SLASH_FROMATER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static DateTimeFormatter DEFAULT_DATE_FORMATER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
 
    
    public static Integer DDMMYY_FORMAT = 1;
    public static Integer DDMMYYY_SLASH_FORMAT = 2;
    public static Integer YYYYMMDD_FORMAT = 3;
    
    
    public static java.sql.Date now() {
        return  toDate(LocalDate.now());
    }
    
    //Chuyển đổi String sang Date -- delfaut format = DATE_SLASH_FORMAT
    public static java.sql.Date toDate(String date) {
            return java.sql.Date.valueOf(convertToLocalDate(date));      
    }
    
    public static java.sql.Date toDate(Object date) {
        if(date instanceof java.util.Date)
            return java.sql.Date.valueOf(convertToLocalDate((Date)date));
        else if(date instanceof LocalDate)
            return java.sql.Date.valueOf((LocalDate) date);
        
        return null;
    }

    //Chuyển đổi từ Date sang String
    public static String toString(Date date) {
//        formater.applyPattern("dd/MM/yyyy");
        return toString(date,DateHelper.DDMMYYY_SLASH_FORMAT);
    }
    
    public static String toString(Date date, int type) {
        return geDatetFormat(type).format(date);
    }
    
    public static String toString(LocalDateTime ldt) {
        return getDatePart(ldt).toString();
    }
    
   
    
    public static String toString(LocalDateTime ldt, int type) {
        return toString(getDatePart(ldt),type);
    }
    
    private static DateFormat geDatetFormat(int type) {
        switch(type) {
            case 1: return DATE_FORMAT;
            case 2: return DATE_SLASH_FORMAT;
            case 3: return DEFAULT_DATE_FORMAT;
            
            default : return DATE_SLASH_FORMAT;
        }  
    }
    
    public static java.sql.Date toDate(String value, int type)  {
        if (value == null) {
            throw new RuntimeException("Chuỗi cần parse là null");
        }
        return java.sql.Date.valueOf(convertToLocalDate(value, type));
    }
     
    public static LocalDate convertToLocalDate(String value) {       
        return convertToLocalDate(value, DDMMYYY_SLASH_FORMAT);
    }
    
    public static java.util.Date addYears(java.util.Date date, int number) {
        return toDate(convertToLocalDate(date).plus(number, ChronoUnit.YEARS));
    }
    
    public static java.util.Date addDays(java.util.Date date, int number) {
        return toDate(convertToLocalDate(date).plus(number, ChronoUnit.DAYS));
    }
    
    public static java.util.Date addMonths(java.util.Date date, int number) {
        return toDate(convertToLocalDate(date).plus(number, ChronoUnit.MONTHS));
    }
    
     
    public static LocalDate convertToLocalDate(String value, int type) {       
//        return LocalDate.parse(value, getFormatter(type));
        try {
            DateTimeFormatter f = getFormatter(type);
            return LocalDate.parse(value, f);
            
        }catch(DateTimeParseException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Không thể chuyển đổi chuỗi sang ngày");
        }
    }
    
    private static DateTimeFormatter getFormatter(int type) {
        switch(type) {
            case 1: return DATE_FROMATER;
            case 2: return DATE_SLASH_FROMATER;
            case 3: return DEFAULT_DATE_FORMATER;
            
            default : return DATE_SLASH_FROMATER;
        }        
    }
    
    public static java.util.Date convertToDate(LocalDate d) {
       return  Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
    public static int compare(java.sql.Date d1, java.sql.Date d2) {
        return d1.compareTo(d2);
    } 
    
    public static int compare(java.util.Date d1, java.util.Date d2) {
        return toDate(d1).compareTo(toDate(d2));
    }
     
    public static LocalDate convertToLocalDate(Date d) {
        return Instant.ofEpochMilli(d.getTime())
            .atZone(ZoneId.systemDefault())
            .toLocalDate();
    }
    
    
    
    public static java.sql.Date getDatePart(LocalDateTime ldt) {
        return DateHelper.toDate(ldt.toLocalDate());
    }
     
    public static void main(String[] args) {
        Date d = DateHelper.toDate("23/02/1998");
        System.out.println(d);
    }
    
    public static String toStringForQuery(Date date) {
        return toString(date,YYYYMMDD_FORMAT);
    }
    
    public static Date getBeginningOfMonth() {
        LocalDate today = LocalDate.now();
        LocalDate firstOfMonth = today.with( ChronoField.DAY_OF_MONTH , 1) ;
        return convertToDate(firstOfMonth);
    }
     
    public static Date getLastDayOfMonth() {
        LocalDate lastDayOfMonth = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        return convertToDate(lastDayOfMonth);
    }
    

    public static LocalDateTime toLocalDateTime(java.sql.Date date, java.sql.Time time) {
        return LocalDateTime.of(DateHelper.convertToLocalDate(date), TimeHelper.toLocalTime(time));
    }
    
    public static int compare (LocalDateTime dt1, LocalDateTime dt2) {
        return dt1.compareTo(dt2);
    }
    
    public static int getYearPart(java.util.Date date){       
        return convertToLocalDate(date).getYear();
    }
}
