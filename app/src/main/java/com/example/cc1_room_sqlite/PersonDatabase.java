package com.example.cc1_room_sqlite;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Person.class},version = 1,exportSchema = false)
public abstract class PersonDatabase extends RoomDatabase {
    public abstract PersonDao personDao();
    public static PersonDatabase instance;
    public static PersonDatabase getInstance(Context context){
        if (instance == null){
            synchronized (PersonDao.class){
                if (instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            PersonDatabase.class,"personDB")
                            .build();
                }
            }
        }
        return instance;
    }
}
