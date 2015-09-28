package me.codethink.heyoffice;

/**
 * Created by archie on 15/9/28.
 */
public class SettingItem {
    private final String mName;
    private final String mValue;

    public SettingItem(String name, String value) {
        mName = name;
        mValue = value;
    }

    public String getName() {
        return mName;
    }

    public String getValue() {
        return mValue;
    }
}
