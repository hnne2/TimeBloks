package com.example.timeblocks;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface DayDao {
         @Update
        public void updateDay(Day day);

        @Query("SELECT * FROM days")
        List<Day> getAllDays();

        @Query("SELECT * FROM days WHERE dayData ==:dayData")
        Day findByDate(String dayData);

        @Insert
        void insertAll(Day... days);

        @Delete
        void delete(Day day);

        @Query("UPDATE days SET day_Info = :dayInfo WHERE dayData = :dayData")
        void updateDayInfoByDayData(String dayInfo, String dayData);

}
