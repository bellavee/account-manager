package com.sales.accountmanager.utils;

import org.springframework.util.ObjectUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DateUtils {

    public static Date dateParse(String date) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(date);
        } catch (ParseException e) {
            return new Date();
        }
    }

    public static String dateFormat(Date date) {
        return ObjectUtils.isEmpty(date) ? "" : new SimpleDateFormat("dd/MM/yyyy").format(date);
    }

    public static String dateHoursFormat(Date date) {
        return ObjectUtils.isEmpty(date) ? "" : new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(date);
    }

    public static Date asDate(LocalDateTime localDateTime) {
        return (localDateTime == null ? null :
                Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()));
    }

    public static LocalDate localDateParse(String dateString) {
        return LocalDate.parse(dateString);
    }

    public static LocalDate localDateParseClassic(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date, formatter);
    }

    public static LocalTime localTimeParse(String timeString) {
        if (timeString == null) {
            return null;
        }

        try {
            return LocalTime.parse(timeString);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public static String localDateFormat(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(formatter);
    }

    public static String localDateFormatClassic(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter);
    }

    public static String localDateFormatName(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("eee dd/MM/yy", Locale.FRANCE);
        return date.format(formatter);
    }

    public static String localTimeFormat(LocalTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return time.format(formatter);
    }

    public static String getWeekdayRange(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startOfWeek = date.with(DayOfWeek.MONDAY);
        LocalDate endOfWeek = date.with(DayOfWeek.SUNDAY);
        return String.format("%s - %s",
                startOfWeek.format(formatter),
                endOfWeek.format(formatter));
    }

    public static String getDateRangeForMonth(YearMonth yearMonth) {
        LocalDate firstDayOfMonth = yearMonth.atDay(1);
        LocalDate lastDayOfMonth = yearMonth.atEndOfMonth();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String firstDayString = firstDayOfMonth.format(formatter);
        String lastDayString = lastDayOfMonth.format(formatter);

        return firstDayString + " - " + lastDayString;
    }

    public static LocalDate getPreviousMonday(LocalDate date) {
        LocalDate prevWeekday = date.minusWeeks(1);
        return prevWeekday.with(DayOfWeek.MONDAY);
    }

    public static LocalDate getNextMonday(LocalDate date) {
        LocalDate nextWeekday = date.plusWeeks(1);
        return nextWeekday.with(DayOfWeek.MONDAY);
    }

    public static List<String> getWeekDays(LocalDate monday) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("eee dd MMM", Locale.FRANCE);
        List<String> weekDays = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            LocalDate day = monday.plusDays(i);
            weekDays.add(day.format(formatter));
        }
        return weekDays;
    }

    public static YearMonth yearMonthParse(String month) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-yyyy");
        return YearMonth.parse(month, formatter);
    }

    public static String convertMonthYearFormat(String inputDate) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MM/yyyy");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MM-yyyy");
        YearMonth yearMonth = YearMonth.parse(inputDate, inputFormatter);
        return yearMonth.format(outputFormatter);
    }

    public static String convertDateFormat(String inputDate) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(inputDate, inputFormatter);
        return date.format(outputFormatter);
    }

}
