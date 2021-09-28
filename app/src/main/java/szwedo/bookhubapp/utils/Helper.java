package szwedo.bookhubapp.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Helper {
    public static String getDefaultDateFormat(Date date){
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd", Locale.ENGLISH);
        return dateFormat.format(date);
    }

    public static String getShortDate(Date date){
        DateFormat dateFormat = new SimpleDateFormat("MMMM dd", Locale.ENGLISH);
        return dateFormat.format(date);
    }

    public static String getReservationDate(Date date){
        DateFormat dateFormat = new SimpleDateFormat("MMMM dd", Locale.ENGLISH);Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,3);
        return dateFormat.format(calendar.getTime());
    }
}
