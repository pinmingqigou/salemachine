package com.freeme.freemelite.salemachine.models.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Arrays;

@Entity(tableName = "allapps")
public class AppEntity {
    @PrimaryKey(autoGenerate = true)
    private int _id;

    @ColumnInfo(name = EntityConfig.COLUMN_CLASS_NAME)
    private String className;

    @ColumnInfo(name = EntityConfig.COLUMN_PACKAGE_NAME)
    private String packageName;

    @ColumnInfo(name = EntityConfig.COLUMN_APP_NAME)
    private String appName;

    @ColumnInfo(name = EntityConfig.COLUMN_ICON)
    private byte[] icon;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "AppEntity{" +
                "_id=" + _id +
                ", className='" + className + '\'' +
                ", packageName='" + packageName + '\'' +
                ", icon=" + Arrays.toString(icon) +
                '}';
    }

    class EntityConfig {
        public static final String COLUMN_APP_NAME = "app_name";

        public static final String COLUMN_CLASS_NAME = "class_name";

        public static final String COLUMN_PACKAGE_NAME = "package_name";

        public static final String COLUMN_ICON = "_icon";
    }
}
