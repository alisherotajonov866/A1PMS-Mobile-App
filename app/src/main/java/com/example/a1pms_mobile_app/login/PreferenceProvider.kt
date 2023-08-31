package com.example.a1pms_mobile_app.login

import android.content.Context
import android.content.SharedPreferences

object SharedPrefsUtils {

    private const val PREF_NAME = "user_pref"
    private const val KEY_TOKEN = "token"
    // private const val KEY_USER_NAME = "user_name"
    // Add other keys for user details

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun saveToken(context: Context, token: String) {
        getSharedPreferences(context).edit().putString(KEY_TOKEN, token).apply()
    }

    fun getToken(context: Context): String? {
        return getSharedPreferences(context).getString(KEY_TOKEN, null)
    }

    // Add methods to save and retrieve user details
}


