package me.codethink.heyoffice;

import android.support.annotation.NonNull;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import me.codethink.heyoffice.greendao.AlarmDataItem;

/**
 * Created by archie on 15/9/18.
 */
public class Alarm implements Comparable<Alarm>{
    private final int mHour;
    private final int mMinute;
    private final long timeInMillis;

    private Alarm(AlarmDataItem alarmDataItem) {
        mHour = alarmDataItem.getHour();
        mMinute = alarmDataItem.getMinute();

        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, mHour);
        calendar.set(Calendar.MINUTE, mMinute);
        calendar.set(Calendar.SECOND, 0);

        timeInMillis = calendar.getTimeInMillis();
    }

    public static Alarm fromDataItem(AlarmDataItem alarmDataItem) {
        return new Alarm(alarmDataItem);
    }

    public String getFormattedAlarmTime() {
        StringBuilder stringBuilder = new StringBuilder();
        if (mHour < 10) {
            stringBuilder.append("0");
        }
        stringBuilder.append(mHour);
        stringBuilder.append(":");
        if (mMinute < 10) {
            stringBuilder.append("0");
        }
        stringBuilder.append(mMinute);

        return stringBuilder.toString();
    }

    public long getTimeInMillis() {
        return timeInMillis;
    }

    @Override
    public int compareTo(@NonNull Alarm alarm) {
        if (timeInMillis < alarm.getTimeInMillis()) {
            return  -1;
        } else if (timeInMillis > alarm.getTimeInMillis()) {
            return 1;
        } else {
            return 0;
        }
    }
}
