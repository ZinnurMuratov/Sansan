package sansan.ru.rockylabs.sansan.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Zinnur on 22.12.16.
 */

public class DateUtil {
    public static String getDate(){
        DateFormat df = new SimpleDateFormat("HH:mm, dd-MM-yyy");
        String date = df.format(Calendar.getInstance().getTime());
        return date;
    }
}
