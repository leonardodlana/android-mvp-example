package com.example.feature.domain

import com.example.feature.domain.model.UserDetails
import io.reactivex.Completable

/**
 *  Add any functions that belong to the local data source here i.e. save something on disk
 */
interface UserLocalDataSource : UserDataSource {

    fun saveUserDetails(userDetails: UserDetails) : Completable

}