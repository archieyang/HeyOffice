package me.codethink.heyoffice;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.codethink.heyoffice.data.Database;
import me.codethink.heyoffice.greendao.AlarmDataItem;
import rx.Observable;
import rx.subjects.BehaviorSubject;

/**
 * Created by archie on 15/8/25.
 */
public class AlarmStore {
    public static final String ALARM_ALERT_ACTION = "me.codethink.heyoffice.alarm";
    public static final String CANCEL_TIME_DATA = "cancel-time-date";

    private static AlarmStore mSingleton = null;
    private final android.app.AlarmManager mAlarmManager;
    private final  Context mContext;
    private PendingIntent pi = null;
    private BehaviorSubject<List<Alarm>> mListBehaviorSubject = BehaviorSubject.create();

    private AlarmStore(Context context) {
        mContext = context;
        mAlarmManager = (android.app.AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        refreshData();
    }

    public static void startUp(Context context) {
        mSingleton = new AlarmStore(context);
    }

    public static AlarmStore get() {
        return mSingleton;
    }

    public void addAlarm(int hour, int minute) {
        AlarmDataItem alarmDataItem = new AlarmDataItem(null, hour, minute);
        Database.get().getSession().insert(alarmDataItem);
        Intent intent = new Intent(ALARM_ALERT_ACTION);
        PendingIntent sender = PendingIntent.getBroadcast(
                mContext, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        mAlarmManager.set(android.app.AlarmManager.RTC_WAKEUP, Alarm.fromDataItem(alarmDataItem).getTimeInMillis(), sender);

        refreshData();
    }

    private void refreshData() {

        Observable
                .just(Database.get().getSession().getAlarmDataItemDao().queryBuilder().list())
                .map(alarmDataItems -> {
                    List<Alarm> alarms = new ArrayList<Alarm>();
                    for (AlarmDataItem item : alarmDataItems) {
                        alarms.add(Alarm.fromDataItem(item));
                    }

                    Collections.sort(alarms);
                    return alarms;
                })
                .subscribe(mListBehaviorSubject::onNext);
    }

    public Observable<List<Alarm>> getListObservable() {
        return mListBehaviorSubject;
    }

    public void cancel() {
        mAlarmManager.cancel(pi);
    }
}
