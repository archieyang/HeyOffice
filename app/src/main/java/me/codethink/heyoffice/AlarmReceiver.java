package me.codethink.heyoffice;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

/**
 * Created by archie on 15/8/23.
 */
public class AlarmReceiver extends WakefulBroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        intent.setClass(context, AlarmService.class);
        context.startService(intent);
    }
}
