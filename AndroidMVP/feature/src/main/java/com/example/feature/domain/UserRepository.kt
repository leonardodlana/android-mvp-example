package com.example.feature.domain

/**
 * Our repository needs to conform local & remote data source
 */
interface UserRepository : UserLocalDataSource, UserRemoteDataSource {
}