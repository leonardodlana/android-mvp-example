package com.example.androidmvp.di

import com.example.common.LocalCache
import com.example.common.LocalCacheImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Provides
    @Singleton
    fun provideLocalCache() : LocalCache {
        return LocalCacheImpl()
    }

}