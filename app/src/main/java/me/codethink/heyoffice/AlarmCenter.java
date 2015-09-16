package me.codethink.heyoffice;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

/**
 * Created by archie on 15/8/25.
 */
public class AlarmCenter {
    public static final String ALARM_ACTION = "me.codethink.heyoffice.alarm";
    public static final String CANCEL_TIME_DATA = "cancel-time-date";

    private static AlarmCenter mSingleton = null;
    private final AlarmManager mAlarmManager;
    private final  Context mContext;
    private PendingIntent pi = null;
    private ArrayList<Long> mAlarmTime = new ArrayList<Long>();

    private AlarmCenter(Context context) {
        mContext = context;
        mAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    }

    public static void startUp(Context context) {
        mSingleton = new AlarmCenter(context);
    }

    public static AlarmCenter get() {
        return mSingleton;
    }

    public void setAlarm(long startInMillis, long endInMillis, long intervalInMillis) {

        final Intent intent = new Intent();
        intent.setAction(ALARM_ACTION);
        intent.putExtra(CANCEL_TIME_DATA, endInMillis - intervalInMillis);

        pi = PendingIntent.getBroadcast(mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        mAlarmManager.setRepeating(AlarmManager.RTC_WAKEUP, startInMillis, intervalInMillis, pi);

        for(long time = startInMillis; time < endInMillis; time += intervalInMillis) {
            mAlarmTime.add(time);
        }
    }

    public ArrayList<Long> getAlarmTime() {
        return mAlarmTime;
    }

    public void cancel() {
        mAlarmManager.cancel(pi);
    }
}
