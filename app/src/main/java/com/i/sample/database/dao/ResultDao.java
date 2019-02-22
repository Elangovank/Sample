package com.i.sample.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.i.sample.database.models.Results;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ResultDao {

    @Insert
    void insert(Results result);


    @Query("select * from results")
    List<Results> getresult();

    @Query("select * from results where userId  = :userid ")
    List<Results> getresultbyUser(String userid);
}
