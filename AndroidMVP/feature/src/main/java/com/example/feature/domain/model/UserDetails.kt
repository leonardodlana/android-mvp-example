package com.example.feature.domain.model

import androidx.annotation.Keep

@Keep
data class UserDetails(
    val id: Int,
    val email: String,
    val firstName: String,
    val lastName: String,
    val avatarURL: String
)
