package me.codethink.heyoffice;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by archie on 15/9/18.
 */
public class Alarm implements Comparable<Alarm>{
    private final int mHour;
    private final int mMinute;
    private final long timeInMillis;

    public Alarm(int hour, int minute) {
        mHour = hour;
        mMinute = minute;

        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, mHour);
        calendar.set(Calendar.MINUTE, mMinute);
        calendar.set(Calendar.SECOND, 0);

        timeInMillis = calendar.getTimeInMillis();
        Log.v("ar_log", timeInMillis + " calc");
        Log.v("ar_log", System.currentTimeMillis() + " now");
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
