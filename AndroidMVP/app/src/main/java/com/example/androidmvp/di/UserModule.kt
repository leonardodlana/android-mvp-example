package com.example.androidmvp.di

import com.example.common.LocalCache
import com.example.common.annotations.FragmentScoped
import com.example.common.annotations.RenderingScheduler
import com.example.feature.domain.*
import com.example.feature.presentation.UserDetailsContract
import com.example.feature.presentation.UserDetailsPresenter
import com.example.feature.ui.UserDetailsFragment
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import io.reactivex.Scheduler
import javax.inject.Singleton

@Module(
    includes = [
        UserModule.DataModule::class
    ]
)
abstract class UserModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun bindUserFragment(): UserDetailsFragment

    @Module
    class DataModule {

        @Provides
        @Singleton
        fun provideUserLocalDataSource(localCache: LocalCache) : UserLocalDataSource {
            return UserLocalDataSourceImpl(localCache)
        }

        @Provides
        @Singleton
        fun provideUserRemoteDataSource(userApi: UserApi) : UserRemoteDataSource {
            return UserRemoteDataSourceImpl(userApi)
        }

        @Provides
        @Singleton
        fun provideUserRepository(userLocalDataSource: UserLocalDataSource, userRemoteDataSource: UserRemoteDataSource) : UserRepository {
            return UserRepositoryImpl(userLocalDataSource, userRemoteDataSource)
        }

        @Provides
        @Singleton
        fun provideUserDetailsPresenter(repository: UserRepository,  @RenderingScheduler renderingScheduler: Scheduler) : UserDetailsContract.Presenter {
            return UserDetailsPresenter(repository, renderingScheduler)
        }
    }

}