package com.example.a1pms_mobile_app.preference

import android.content.Context
import android.content.SharedPreferences
import com.example.a1pms_mobile_app.network.Organization
import com.example.a1pms_mobile_app.network.User

object SharedPrefsUtils {

    private const val PREF_NAME = "user_pref"
    private const val KEY_TOKEN = "token"
    private const val KEY_NAME = "name"
    private const val KEY_EMAIL = "email"
    private const val KEY_PHONE = "phone"
    private const val KEY_ORG_NAME = "org_name"
    private const val KEY_STIR = "stir"

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun saveToken(context: Context, token: String) {
        getSharedPreferences(context).edit().putString(KEY_TOKEN, token).apply()
    }

    fun getToken(context: Context): String? {
        return getSharedPreferences(context).getString(KEY_TOKEN, null)
    }

    fun saveUserData(context: Context, user: User) {
        with(getSharedPreferences(context).edit()) {
            putString(KEY_NAME, user.name)
            putString(KEY_EMAIL, user.email)
            putString(KEY_PHONE, user.phoneNumber)
            putString(KEY_ORG_NAME, user.organization.name)
            putString(KEY_STIR, user.organization.stir)
            apply()
        }
    }

    fun getUserData(context: Context): User? {
        val prefs = getSharedPreferences(context)
        return if (prefs.contains(KEY_NAME)) {
            val name = prefs.getString(KEY_NAME, "") ?: ""
            val email = prefs.getString(KEY_EMAIL, "") ?: ""
            val phone = prefs.getString(KEY_PHONE, "") ?: ""
            val orgName = prefs.getString(KEY_ORG_NAME, "") ?: ""
            val stir = prefs.getString(KEY_STIR, "") ?: ""
            User(name, phone, email, Organization(orgName, stir))
        } else {
            null
        }
    }

    fun clearToken(context: Context) {
        getSharedPreferences(context).edit().remove(KEY_TOKEN).apply()
    }

    fun clearUserDataFromPrefs(context: Context) {
        val sharedPreferences = context.getSharedPreferences("UserData", Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()
    }
}
