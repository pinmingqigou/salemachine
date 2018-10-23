package com.freeme.freemelite.salemachine.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.freeme.freemelite.salemachine.dao.AllAppsDao;
import com.freeme.freemelite.salemachine.models.entity.AppEntity;

@Database(entities = {AppEntity.class}, version = 1,exportSchema = false)
public abstract class AllAppsDatabase extends RoomDatabase {
    public abstract AllAppsDao allAppsDao();


    public class DatabaseConfig {
        public static final String DATABASE_FILE_NAME = "sale_machine_all_apps";
    }
}
