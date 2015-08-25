package me.codethink.heyoffice;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Created by archie on 15/8/23.
 */
public class AlarmService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v("ar_log", intent.getStringExtra(MainActivity.ALARM_ACTION));

        SharedPreferences sharedPreferences = getSharedPreferences("alarmd", MODE_PRIVATE);


        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        if (alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        }
        Ringtone ringtone = RingtoneManager.getRingtone(this, alarmUri);
        ringtone.play();
        
        showNotification();

        int current = sharedPreferences.getInt("times", 0);
        ++ current;
        sharedPreferences.edit().putInt("times", current).apply();
        Log.v("ar_log", "cancel" + current);
        if (current == 5) {
            Log.v("ar_log", "cancel");
            AlarmCenter.get().cancel();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    public void showNotification() {
        NotificationCompat.Builder builder= new NotificationCompat.Builder(this);
        Intent i = new Intent(this, MainActivity.class);

        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pi= PendingIntent.getActivity(this, 0,
                i, 0);

        builder.setContentIntent(pi)
                .setSmallIcon(R.drawable.abc_btn_switch_to_on_mtrl_00001)
//                .setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.some_big_img))
//                .setTicker(res.getString(R.string.your_ticker))
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentTitle("hi")
                .setContentText("hi");

//        startForeground(1337, builder.build());
        NotificationManager nm = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);

        nm.notify(1, builder.build());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
