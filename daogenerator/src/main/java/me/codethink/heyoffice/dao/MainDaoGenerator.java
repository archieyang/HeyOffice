package me.codethink.heyoffice.dao;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MainDaoGenerator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "me.codethink.heyoffice.greendao");
        Entity alarmItem = schema.addEntity("AlarmDataItem");
        alarmItem.addIdProperty();
        alarmItem.addIntProperty("hour");
        alarmItem.addIntProperty("minute");

        new DaoGenerator().generateAll(schema, "app/src/main/java-gen");
    }
}
