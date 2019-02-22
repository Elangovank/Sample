package com.i.sample.database.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.i.sample.database.models.Category;

import java.util.List;

@Dao
public interface categoryDao {

    @Insert
    void insertCategory(Category category);

    @Query("select * from category")
    List<Category> getCategoryList();

    @Query("select name from category")
    List<String> getCategoryNameList();
}
