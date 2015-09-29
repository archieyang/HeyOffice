package me.codethink.heyoffice.utils.ui;

/**
 * Created by archie on 15/9/29.
 */
public class TimeUtils {
    public static String hourMinuteToFormattedTime(int hour, int minute) {
        StringBuilder stringBuilder = new StringBuilder();
        if (hour < 10) {
            stringBuilder.append("0");
        }
        stringBuilder.append(hour);
        stringBuilder.append(":");
        if (minute < 10) {
            stringBuilder.append("0");
        }
        stringBuilder.append(minute);

        return stringBuilder.toString();
    }
}
