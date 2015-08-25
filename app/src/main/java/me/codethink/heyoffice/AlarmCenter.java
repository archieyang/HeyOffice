package me.codethink.heyoffice;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/**
 * Created by archie on 15/8/25.
 */
public class AlarmCenter {
    private static AlarmCenter mSingleton = null;
    private static AlarmManager mInstance = null;
    private static Context mContext = null;
    private PendingIntent pi = null;

    public static void startUp(Context context) {
        mContext = context;
        mInstance = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        mSingleton = new AlarmCenter();

    }

    public static AlarmCenter get() {
        return mSingleton;
    }

    public void start() {

        mContext.getSharedPreferences("alarmd", Context.MODE_PRIVATE).edit().putInt("times", 0).apply();
        final Intent intent = new Intent();
        intent.setAction(MainActivity.ALARM_ACTION);
        intent.putExtra(MainActivity.ALARM_ACTION, "Hey office!");

        pi = PendingIntent.getBroadcast(mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        mInstance.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 5000, 5000, pi);
    }

    public void cancel() {
        mInstance.cancel(pi);
    }
}
