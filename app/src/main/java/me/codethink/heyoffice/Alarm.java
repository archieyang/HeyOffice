package me.codethink.heyoffice;

import android.support.annotation.NonNull;

import java.util.Calendar;
import java.util.Date;

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

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), mHour, mMinute);

        timeInMillis = calendar.getTimeInMillis();
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
