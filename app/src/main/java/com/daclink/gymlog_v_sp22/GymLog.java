package com.daclink.gymlog_v_sp22;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.daclink.gymlog_v_sp22.DB.AppDataBase;

import java.util.Date;

@Entity(tableName = AppDataBase.GYMLOG_TABLE)
public class GymLog {

    @PrimaryKey(autoGenerate = true)
    //declare primary key for table
    private int mLogId;
    private String mExercise;
    private Double mWeight;
    private int mReps;
    private Date mDate;

    public GymLog(String exercise, Double weight, int reps) {
        mExercise = exercise;
        mWeight = weight;
        mReps = reps;
        mDate = new Date();
    }

    public String getExercise() {
        return mExercise;
    }

    public void setExercise(String exercise) {
        mExercise = exercise;
    }

    public Double getWeight() {
        return mWeight;
    }

    public void setWeight(Double weight) {
        mWeight = weight;
    }

    public int getReps() {
        return mReps;
    }

    public void setReps(int reps) {
        mReps = reps;
    }

    @Override
    public String toString() {
        return "Log # " + mLogId + "\n" +
                "Exercise: " + mExercise + "\n" +
                "Weight: " + mWeight + "\n" +
                "Reps: " + mReps + "\n" +
                "Date: " + mDate + "\n" +
                "=-=-=-=-=-=-=-=-=-=-\n";
    }

    public int getLogId() {
        return mLogId;
    }

    public void setLogId(int logId) {
        mLogId = logId;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }
}
