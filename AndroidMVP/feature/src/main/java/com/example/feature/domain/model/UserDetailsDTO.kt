package com.example.feature.domain.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class UserDetailsDTO(val data: UserDetailsDataDTO) {

    @Keep
    data class UserDetailsDataDTO(
        val id: Int,
        val email: String,
        @SerializedName("first_name") val firstName: String,
        @SerializedName("last_name") val lastName: String,
        val avatar: String
    )

    fun toDomainModel(): UserDetails {
        return UserDetails(
            data.id,
            data.email,
            data.firstName,
            data.lastName,
            data.avatar
        )
    }

}

