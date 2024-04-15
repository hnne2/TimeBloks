package com.example.timeblocks;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Day.class},version = 1)
abstract public class DaysAppDataBase extends RoomDatabase {
    public abstract DayDao getDayDao();
}
