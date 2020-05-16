package com.oconte.david.mynews.Utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ConfigureDate {

    private static final SimpleDateFormat DATE_FORMAT_API =
            new SimpleDateFormat("yyyyMMdd", Locale.FRENCH);
    private static final SimpleDateFormat DATE_FORMAT_DISPLAY =
            new SimpleDateFormat("dd/MM/yy", Locale.FRENCH);
    private static final SimpleDateFormat DATE_FORMAT_FROM_API =
            new SimpleDateFormat("yyyy-MM-dd", Locale.FRENCH);

    /**
     * Convert the date from API to use this in the format for recyclerview
     *
     * @param dateString
     * @return
     */
    public static String convertDateFromAPIToDisplay(String dateString) {
        String[] arrayDate = dateString.split("T");
        Date date = new Date();
        try {
            date = DATE_FORMAT_FROM_API.parse(arrayDate[0]);
        } catch (ParseException e) {
            return null;
        }

        return DATE_FORMAT_DISPLAY.format(date);

    }

    /**
     * @param time
     * @return
     */
    public static String convertDateForAPI(String time) {
        String[] arrayDate = time.split("T");
        Date date = new Date();

        try {
            date = DATE_FORMAT_DISPLAY.parse(arrayDate[0]);

        } catch (ParseException e) {
            return null;
        }
        return DATE_FORMAT_API.format(date);
    }


    /**
     * It's for control date
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static boolean compareDate(String beginDate, String endDate) {
        Calendar beginCal = Calendar.getInstance();
        Calendar endCal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH);
        try {
            beginCal.setTime(sdf.parse(beginDate));
        } catch (ParseException e) {
            if (!"".equals(beginDate)) {
                return false;
            }

        }

        try {
            endCal.setTime(sdf.parse(endDate));
        } catch (ParseException e) {
            if (!"".equals(endDate)) {
                return false;
            }
        }


        Calendar dateToDay = Calendar.getInstance();

        if ("".equals(beginDate) && "".equals(endDate)) {
            return true;
        }

        if (!"".equals(beginDate) && !"".equals(endDate) && isSameDay(beginCal,endCal )) {

            return true;
        }

        if (!"".equals(endDate) && isSameDay(dateToDay,endCal )) {

            return true;
        }

        if (!"".equals(beginDate) && isSameDay(dateToDay,beginCal )) {

            return true;
        }

        return false;

    }

    private static boolean isSameDay(Calendar cal1, Calendar cal2){
        boolean sameDay = cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
        return sameDay;
    }

    /*private static boolean isDayBefore(){
        return ;
    }

    private static boolean isDayAfter(){
        return ;
    }*/


}
