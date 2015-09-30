package me.codethink.heyoffice;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.subjects.BehaviorSubject;

/**
 * Created by archie on 15/9/28.
 */
public class SettingStore {

    public enum Type {
        Time,
        Text
    }
    private static SettingStore mSingleton = null;

    private BehaviorSubject<List<SettingItem>> mSettingListSubject = BehaviorSubject.create();

    private SettingStore(Context context) {
        Resources res = context.getResources();

        List<SettingItem> settingItemList = new ArrayList<SettingItem>();

        for(String name : Arrays.asList(res.getStringArray(R.array.setting_titles))) {
            SettingItem settingItem = new SettingItem(name, "value", Type.Time);

            Gson gson = new Gson();
            // TODO: 15/9/30 load and save settings 
            Log.v("ar_log", gson.toJson(settingItem));
            settingItemList.add(settingItem);
        }
        mSettingListSubject.onNext(settingItemList);
    }

    public static void startup(Context context) {
        mSingleton = new SettingStore(context);
    }

    public Observable<List<SettingItem>> getSettingListObservable() {
        return mSettingListSubject;
    }

    public static SettingStore get() {
        return mSingleton;
    }
}
