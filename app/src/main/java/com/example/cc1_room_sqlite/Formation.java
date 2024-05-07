package com.example.cc1_room_sqlite;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "formation")
public class Formation {
    @PrimaryKey (autoGenerate = true)
    public int id;
    public String nameFormation;
    public int dureeFormation;
    public String typeFormation;
    public static int cmp;

    public Formation(String nameFormation, int dureeFormation, String typeFormation){
        this.id = cmp++;
        this.nameFormation = nameFormation;
        this.dureeFormation = dureeFormation;
        this.typeFormation = typeFormation;
    }

    @Override
    public String toString() {
        return "id=" + id +", nameFormation='" + nameFormation + ", dureeFormation=" + dureeFormation +
                ", typeFormation='" + typeFormation;
    }
}
