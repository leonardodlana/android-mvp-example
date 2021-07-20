package com.example.feature.domain

import com.example.common.ResultWrapper
import com.example.feature.domain.model.UserDetails
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.ObservableSource

class UserRepositoryImpl(
    private val localDataSource: UserLocalDataSource,
    private val remoteDataSource: UserRemoteDataSource
) : UserRepository {

    override fun saveUserDetails(userDetails: UserDetails): Completable {
        return localDataSource.saveUserDetails(userDetails)
    }

    override fun getUserDetails(userId: String): Observable<ResultWrapper<UserDetails>> {
        return Observable.merge(
            localDataSource.getUserDetails(userId),
            getRemoteUserDetails(userId)
        ).firstOrError().toObservable()
    }

    private fun getRemoteUserDetails(userId: String): ObservableSource<ResultWrapper<UserDetails>> {
        return remoteDataSource.getUserDetails(userId)
            .flatMap { result ->
                if (result.isSuccess()) {
                    saveUserDetails(result.model!!)
                        .andThen(Observable.just(result))
                } else {
                    Observable.just(result)
                }
            }
    }

}