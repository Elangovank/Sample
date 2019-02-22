package com.i.sample.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.i.sample.database.models.Questions;

import java.util.List;

@Dao
public interface QuestionsDao {

    @Insert
    void insert(Questions question);

    @Query("select * from questions where categoryname = :catName order by random() limit 10")
    Questions getQuestions(String catName);

    @Query("select * from questions order by random() limit 10")
    List<Questions> getAllQuestions();
}
