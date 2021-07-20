package com.example.feature.domain

import com.example.common.ResultWrapper
import com.example.common.toResult
import com.example.feature.domain.model.UserDetails
import io.reactivex.Observable

class UserRemoteDataSourceImpl(private val userApi: UserApi) : UserRemoteDataSource {

    override fun getUserDetails(userId: String): Observable<ResultWrapper<UserDetails>> {
        return userApi.getUserDetails(userId).map {
            // Transform to domain model
            it.toDomainModel()
        }.toResult()
    }
}