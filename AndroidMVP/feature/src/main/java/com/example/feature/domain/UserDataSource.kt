package com.example.feature.domain

import com.example.common.ResultWrapper
import com.example.feature.domain.model.UserDetails
import io.reactivex.Observable

interface UserDataSource {

    fun getUserDetails(userId: String) : Observable<ResultWrapper<UserDetails>>

}