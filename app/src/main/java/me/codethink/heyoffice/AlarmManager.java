package me.codethink.heyoffice;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by archie on 15/8/25.
 */
public class AlarmManager {
    public static final String ALARM_ALERT_ACTION = "me.codethink.heyoffice.alarm";
    public static final String CANCEL_TIME_DATA = "cancel-time-date";

    private static AlarmManager mSingleton = null;
    private final android.app.AlarmManager mAlarmManager;
    private final  Context mContext;
    private PendingIntent pi = null;
    private ArrayList<Alarm> mAlarms = new ArrayList<Alarm>();

    private AlarmManager(Context context) {
        mContext = context;
        mAlarmManager = (android.app.AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    }

    public static void startUp(Context context) {
        mSingleton = new AlarmManager(context);
    }

    public static AlarmManager get() {
        return mSingleton;
    }

    public void addAlarm(int hour, int minute) {
        Alarm alarm = new Alarm(hour, minute);
        mAlarms.add(alarm);
        Collections.sort(mAlarms);

        Intent intent = new Intent(ALARM_ALERT_ACTION);
        PendingIntent sender = PendingIntent.getBroadcast(
                mContext, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        mAlarmManager.set(android.app.AlarmManager.RTC_WAKEUP, alarm.getTimeInMillis(), sender);

    }

    public ArrayList<Alarm> getAlarmTime() {
        return mAlarms;
    }

    public void cancel() {
        mAlarmManager.cancel(pi);
    }
}
