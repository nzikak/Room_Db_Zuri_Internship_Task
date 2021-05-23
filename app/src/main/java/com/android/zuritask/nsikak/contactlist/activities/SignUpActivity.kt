package com.android.zuritask.nsikak.contactlist.activities

import android.content.Context
import android.content.Intent
import android.hardware.input.InputManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.android.zuritask.nsikak.contactlist.R
import com.android.zuritask.nsikak.contactlist.database.User
import com.android.zuritask.nsikak.contactlist.databinding.ActivitySignUpBinding
import com.android.zuritask.nsikak.contactlist.repository.UserRepository
import com.android.zuritask.nsikak.contactlist.utils.closeKeyboard
import com.android.zuritask.nsikak.contactlist.utils.encodePassword
import com.android.zuritask.nsikak.contactlist.utils.showToast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signUpButton.setOnClickListener {
            closeKeyboard(it, this)
            createNewUser()
        }

        binding.signInButton.setOnClickListener {
            navigateToLogin()
        }
    }

    private fun createNewUser() {
        val name = binding.userNameField.text.toString()
        val email = binding.userEmailField.text.toString()
        val password = binding.userPasswordField.text.toString()
        val confirmPassword = binding.userConfirmPasswordField.text.toString()

        when {
            name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() -> {
                showToast("All fields required!", this)
            }
            password != confirmPassword -> {
                showToast("Password does not match!", this)
            }
            else -> {
                val encodedPass = encodePassword(password)
                val user = User(name = name, email = email, password = encodedPass)
                val repository = UserRepository(this)
                CoroutineScope(Dispatchers.Main).launch {
                    val id = repository.insertUser(user)
                    if(id > 0) {
                        showToast("Account created successfully!", this@SignUpActivity)
                        navigateToLogin()
                    }
                }

            }
        }
    }

    private fun navigateToLogin() {
        val intent = Intent(this@SignUpActivity, LogInActivity::class.java)
        startActivity(intent)
    }

}