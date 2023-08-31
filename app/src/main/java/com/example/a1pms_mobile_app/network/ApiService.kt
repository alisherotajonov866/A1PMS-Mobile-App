package com.example.a1pms_mobile_app.network

import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/api/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): LoginResponse
}

data class LoginRequest(val email: String, val password: String)
data class LoginResponse(val token: String, val user: User)

data class User(val name: String, val lastName: String, val phoneNumber: String)

