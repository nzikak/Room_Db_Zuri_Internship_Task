package com.android.zuritask.nsikak.contactlist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [User::class], version = 1)
abstract class UserDatabase: RoomDatabase() {

    abstract fun getUserDatabaseDao(): UserDatabaseDao

    companion object {

        @Volatile
        var INSTANCE: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if(instance == null) {
                    instance = Room.databaseBuilder(context, UserDatabase::class.java, "user_database")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                    return instance
            }

        }
    }
}