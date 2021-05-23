package com.android.zuritask.nsikak.contactlist.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.zuritask.nsikak.contactlist.R
import com.android.zuritask.nsikak.contactlist.databinding.ActivityLogInBinding
import com.android.zuritask.nsikak.contactlist.repository.UserRepository
import com.android.zuritask.nsikak.contactlist.utils.closeKeyboard
import com.android.zuritask.nsikak.contactlist.utils.encodePassword
import com.android.zuritask.nsikak.contactlist.utils.showToast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val USER_NAME_INTENT_EXTRA_KEY = "username"
class LogInActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signInButton.setOnClickListener {
            closeKeyboard(it, this)
            logInUser()
        }
    }

    private fun logInUser() {
        val email = binding.userEmailField.text.toString()
        val password = binding.userPasswordField.text.toString()
        val encodedPass = encodePassword(password)

        when {
            email.isEmpty() || password.isEmpty() -> {
                showToast("Please enter both email and password", this)
            }
            else -> {
                CoroutineScope(Dispatchers.Main).launch {
                    val repository = UserRepository(this@LogInActivity)
                    val user = repository.getUser(email, encodedPass)
                    if(user == null) {
                        showToast("User not found! Incorrect email or password.", this@LogInActivity)
                    }
                    else {
                        navigateToMainActivity(user.name)
                    }
                }
            }
        }
    }

    private fun navigateToMainActivity(userName: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(USER_NAME_INTENT_EXTRA_KEY, userName)
        startActivity(intent)
        finishAffinity()
    }
}