package com.android.zuritask.nsikak.contactlist.utils

import android.content.Context
import android.util.Base64
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService


fun closeKeyboard(view: View, context: Context) {
    val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(view.windowToken, 0)

}

fun showToast(message: String, context: Context) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun encodePassword(password: String): String {
    return Base64.encodeToString(password.toByteArray(Charsets.UTF_8), Base64.DEFAULT)
}