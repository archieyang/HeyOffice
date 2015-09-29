package me.codethink.heyoffice;

/**
 * Created by archie on 15/9/28.
 */
public class SettingItem {
    private final String mName;
    private String mValue;
    private final SettingStore.Type mType;

    public SettingItem(String name, String value, SettingStore.Type type) {
        mName = name;
        mValue = value;
        mType = type;
    }

    public String getName() {
        return mName;
    }

    public String getValue() {
        return mValue;
    }

    public void setValue(String newValue) {
        mValue = newValue;
    }

    public SettingStore.Type getType() {
        return mType;
    }
}
