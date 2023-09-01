package com.example.a1pms_mobile_app.network

import com.google.gson.annotations.SerializedName
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/api/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): LoginResponse
}

data class LoginRequest(val email: String, val password: String)
data class LoginResponse(
    val token: String,
    val user: User
)

data class User(
    val name: String,
    @SerializedName("phone_number") val phoneNumber: String,
    val email: String,
    val organization: Organization
)

data class Organization(
    val name: String,
    val stir: String
)

