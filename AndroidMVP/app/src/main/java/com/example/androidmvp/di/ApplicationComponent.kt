package com.example.androidmvp.di

import com.example.androidmvp.MyApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidModule::class,
        CacheModule::class,
        NetworkModule::class,
        UserModule::class,
        AndroidSupportInjectionModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<MyApplication> {

    @Component.Builder
    interface Builder {
        fun build(): ApplicationComponent
        fun androidModule(androidModule: AndroidModule): Builder
    }

}