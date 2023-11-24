package com.daclink.gymlog_v_sp22.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.daclink.gymlog_v_sp22.GymLog;

@Database(entities = {GymLog.class}, version = 1)
@TypeConverters({DateTypeConverter.class})
public abstract class AppDataBase extends RoomDatabase {
    public static final String DATABASE_NAME = "Gym_Log.db";
    public static final String GYMLOG_TABLE = "gymlog_table";

    private static volatile AppDataBase instance;
    //volatile means you can only access from main memory; keeps memory intact
    //no race conditions and persistent
    private static final Object LOCK = new Object();
    //make sure nothing else can access database at same time
    public abstract GymLogDAO GymLogDAO();
    public static AppDataBase getInstance(Context context) {
        //singleton
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    //lock and create
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDataBase.class,
                            DATABASE_NAME).build();
                }
            }
        }
        return instance;
    }
}
