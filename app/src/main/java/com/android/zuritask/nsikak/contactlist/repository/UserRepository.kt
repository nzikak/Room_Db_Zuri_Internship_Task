package com.android.zuritask.nsikak.contactlist.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.zuritask.nsikak.contactlist.database.User
import com.android.zuritask.nsikak.contactlist.database.UserDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class UserRepository(context: Context) {

    private val userDB = UserDatabase.getInstance(context)

    suspend fun insertUser(user: User): Long {

        val id = CoroutineScope(Dispatchers.IO).async {
            userDB.getUserDatabaseDao().createUser(user)
        }
        return id.await()
    }

    suspend fun getUser(name: String, password: String): User? {
        val user = CoroutineScope(Dispatchers.IO).async {
            val user = userDB.getUserDatabaseDao().getUser(name, password)
            user
        }
        return user.await()
    }
}