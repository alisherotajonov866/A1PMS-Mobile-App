package com.example.a1pms_mobile_app.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a1pms_mobile_app.network.ApiService
import com.example.a1pms_mobile_app.network.LoginRequest
import com.example.a1pms_mobile_app.network.User
import kotlinx.coroutines.launch

class LoginViewModel (private val apiService: ApiService) : ViewModel() {

    private val _loginState = MutableLiveData<LoginState>()
    val loginState: LiveData<LoginState> get() = _loginState

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = apiService.loginUser(LoginRequest(email, password))
                _loginState.value = LoginState.Success(response.token, response.user)
            } catch (e: Exception) {
                _loginState.value = LoginState.Error(e.message)
            }
        }
    }
}

sealed class LoginState {
    data class Success(val token: String, val user: User) : LoginState()
    data class Error(val message: String?) : LoginState()
}
