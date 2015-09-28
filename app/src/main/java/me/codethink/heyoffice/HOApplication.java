package me.codethink.heyoffice;

import android.app.Application;

import me.codethink.heyoffice.data.Database;

/**
 * Created by archie on 15/9/20.
 */
public class HOApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Database.start(this);
        SettingStore.startup(this);
    }
}
