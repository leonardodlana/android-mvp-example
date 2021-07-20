package com.example.feature.domain

import com.example.common.DataError
import com.example.common.LocalCache
import com.example.common.ResultWrapper
import com.example.feature.domain.model.UserDetails
import io.reactivex.Completable
import io.reactivex.Observable

class UserLocalDataSourceImpl(private val localCache: LocalCache) : UserLocalDataSource {

    override fun saveUserDetails(userDetails: UserDetails) : Completable {
        return localCache.put(userDetails.id.toString(), userDetails)
    }

    override fun getUserDetails(userId: String): Observable<ResultWrapper<UserDetails>> {
        return localCache.get(userId).map { savedValue ->
            val userDetails = savedValue as? UserDetails
            if(userDetails != null) ResultWrapper(userDetails) else ResultWrapper<UserDetails>(null, DataError.GENERIC_ERROR)
        }.toObservable()
    }

}