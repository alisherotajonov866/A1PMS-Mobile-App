package com.example.a1pms_mobile_app.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.a1pms_mobile_app.network.User

class UserDataViewModel: ViewModel() {
    private val _userData = MutableLiveData<User>()
    val userData: LiveData<User> get() = _userData

    fun setUserData(user: User) {
        _userData.value = user
    }
}
