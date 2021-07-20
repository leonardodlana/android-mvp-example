package com.example.feature.domain

import com.example.feature.domain.model.UserDetailsDTO
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {

    @GET("users/{userId}")
    fun getUserDetails(@Path("userId") userId: String) : Single<UserDetailsDTO>

}