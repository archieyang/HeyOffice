package me.codethink.heyoffice.data;

import android.content.Context;

import me.codethink.heyoffice.greendao.DaoMaster;
import me.codethink.heyoffice.greendao.DaoSession;

/**
 * Created by archie on 15/9/20.
 */
public class Database {
    private static Database mDatabase;
    private final DaoMaster mDaoMaster;
    private final DaoSession mDaoSession;

    private Database (Context context) {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, "main-db", null);
        mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
    }

    public static void start(Context context) {
        mDatabase = new Database(context);
    }

    public static Database get() {
        assert mDatabase != null;

        return mDatabase;
    }

    public DaoSession getSession() {
        return mDaoSession;
    }

}
