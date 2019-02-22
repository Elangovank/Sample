package com.i.sample.database.models;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "user")
public class User implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;


    @ColumnInfo(name = "name")
    @NonNull
     public String name = "";

    @ColumnInfo(name = "email")
    @NonNull
    public String email = "";

    @ColumnInfo(name = "mobile")
    @NonNull
    public String mobile = "";

    @ColumnInfo(name = "password")
    @NonNull
    public String password = "";
    @ColumnInfo(name = "status")
    public String status = "1";
}
