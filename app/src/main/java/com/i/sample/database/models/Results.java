package com.i.sample.database.models;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "results")
public class Results implements Serializable {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "userId")
    @NonNull
    public int userId;

    @ColumnInfo(name = "userName")
    @NonNull
    public int userName;

    @ColumnInfo(name = "categoryname")
    @NonNull
    public String categoryname;

    @ColumnInfo(name = "noofquestions")
    @NonNull
    public String noofquestions;

    @ColumnInfo(name = "noofCorrectAnswer")
    @NonNull
    public String noofCorrectAnswer;

    @ColumnInfo(name = "status")
    @NonNull
    public String status;


}



