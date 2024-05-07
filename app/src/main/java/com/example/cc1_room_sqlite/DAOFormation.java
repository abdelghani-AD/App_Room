package com.example.cc1_room_sqlite;

import android.database.Cursor;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DAOFormation {
    @Insert
    void insert(Formation formation);

    @Query("SELECT DISTINCT * FROM formation")
    List<Formation> getData();
    //@Query("Select * from formation where id = :id")
    //void getDataById(Integer id);
}
