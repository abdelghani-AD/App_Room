package com.example.cc1_room_sqlite;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Formation.class},version = 1,exportSchema = false)
public abstract class DataBase extends RoomDatabase {
    public abstract  DAOFormation getDAO();
    public static DataBase instance;
    public static synchronized DataBase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context
                            .getApplicationContext(),DataBase.class,"DB")
                    .build();
        }
        return instance;
    }
}
