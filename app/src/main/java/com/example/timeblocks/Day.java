package com.example.timeblocks;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "days")
public class Day {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "day_Info")
    public String dayInfo;

    @ColumnInfo(name = "dayData")
    public String dayData;

    public Day(String dayInfo,String dayData){

        this.dayData=dayData;
        this.dayInfo=dayInfo;
    }

}
