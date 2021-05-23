package com.android.zuritask.nsikak.contactlist.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDatabaseDao {

    @Insert
    fun createUser(user: User): Long

    @Query("SELECT * FROM user_table WHERE email = :email AND password = :password")
    fun getUser(email: String, password: String): User?
}