package com.example.cc1_room_sqlite;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "person")
public class Person {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String nom;
    public int age;
    public int cmp;
    public Person(String nom, int age) {
        this.id = cmp++;
        this.nom = nom;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getAge() {
        return age;
    }

    public int getCmp() {
        return cmp;
    }
}
