package me.codethink.heyoffice;

import com.google.gson.annotations.SerializedName;

/**
 * Created by archie on 15/9/28.
 */
public class SettingItem {
    @SerializedName("name")
    private final String mName;
    @SerializedName("value")
    private String mValue;
    @SerializedName("type")
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
