package com.i.sample.database.models;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "questions")
public class Questions  implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @SerializedName("id")
    public int id;

    @ColumnInfo(name = "categoryname")
    @NonNull
    @SerializedName("categoryname")
    public String categoryName = "";

    @NonNull
    @ColumnInfo(name = "question")
    @SerializedName("question")
    public String question = "";

    @NonNull
    @ColumnInfo(name = "optionA")
    @SerializedName("optionA")
    public String optionA = "";

    @NonNull
    @ColumnInfo(name = "optionB")
    @SerializedName("optionB")
    public String optionB = "";

    @NonNull
    @ColumnInfo(name = "optionC")
    @SerializedName("optionC")
    public String optionC = "";

    @NonNull
    @ColumnInfo(name = "optionD")
    @SerializedName("optionD")
    public String optionD = "";


    @NonNull
    @ColumnInfo(name = "answer")
    @SerializedName("answer")
    public String answer = "";
}
