package com.freeme.freemelite.salemachine.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.freeme.freemelite.salemachine.models.entity.AppEntity;

import java.util.List;

@Dao
public interface AllAppsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertApps(AppEntity... appEntities);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertApps(List<AppEntity> entities);

    @Update
    void updateApps(AppEntity... appEntities);

    @Delete
    void deleteApps(AppEntity... appEntities);

    @Query("SELECT * FROM allapps")
    List<AppEntity> loadAllApps();

    @Query("SELECT * FROM allapps WHERE package_name LIKE :packageName")
    AppEntity loadSpecifiedApp(String packageName);
}
