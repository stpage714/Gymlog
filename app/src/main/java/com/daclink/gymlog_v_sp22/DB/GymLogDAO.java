package com.daclink.gymlog_v_sp22.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.daclink.gymlog_v_sp22.GymLog;

import java.util.List;

@Dao
public interface GymLogDAO {
    //these are were queries are made; entry point for database
    //created whenever database objects are manipulated
    @Insert
    void insert(GymLog... gymLogs);

    @Update
    void update(GymLog... gymLogs);

    @Delete
    void delete(GymLog gymLogs);
    //only delete single log

    @Query("SELECT * FROM " + AppDataBase.GYMLOG_TABLE + " ORDER BY mDate desc")
        //custom = @Query
    List<GymLog> getGymLogs();

    @Query("SELECT * FROM " + AppDataBase.GYMLOG_TABLE + " WHERE mLogId = :logId")
    List<GymLog> getGymLogsById(int logId);


}
