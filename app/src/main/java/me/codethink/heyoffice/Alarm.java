package me.codethink.heyoffice;

import android.support.annotation.NonNull;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import me.codethink.heyoffice.data.Database;
import me.codethink.heyoffice.greendao.AlarmDataItem;
import me.codethink.heyoffice.utils.ui.TimeUtils;

/**
 * Created by archie on 15/9/18.
 */
public class Alarm implements Comparable<Alarm>{
    private final long mTimeInMillis;
    private final AlarmDataItem mAlarmDataItem;

    private Alarm(AlarmDataItem alarmDataItem) {

        mAlarmDataItem = alarmDataItem;
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, alarmDataItem.getHour());
        calendar.set(Calendar.MINUTE, alarmDataItem.getMinute());
        calendar.set(Calendar.SECOND, 0);

        mTimeInMillis = calendar.getTimeInMillis();
    }

    public static Alarm fromDataItem(AlarmDataItem alarmDataItem) {
        return new Alarm(alarmDataItem);
    }

    public String getFormattedAlarmTime() {
        return TimeUtils.hourMinuteToFormattedTime(mAlarmDataItem.getHour(), mAlarmDataItem.getMinute());
    }

    public long getTimeInMillis() {
        return mTimeInMillis;
    }

    @Override
    public int compareTo(@NonNull Alarm alarm) {
        if (mTimeInMillis < alarm.getTimeInMillis()) {
            return  -1;
        } else if (mTimeInMillis > alarm.getTimeInMillis()) {
            return 1;
        } else {
            return 0;
        }
    }

    public void delete() {
        Database.get().getSession().delete(mAlarmDataItem);
    }
}
