package com.i.sample.database.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.i.sample.database.models.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Query("update user set status = 0 where id = :id")
    void update(int id);

    @Query("select * from user")
    List<User> getUsers();

    @Query("select * from user where email = :email and password= :password")
    User getLoginUser(String email, String password);
}
