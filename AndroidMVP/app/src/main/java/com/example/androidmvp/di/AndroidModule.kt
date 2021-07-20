package com.example.androidmvp.di

import android.app.Application
import android.content.Context
import com.example.androidmvp.MyApplication
import com.example.common.annotations.ApplicationContext
import com.example.common.annotations.RenderingScheduler
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

@Module
class AndroidModule(private val application: MyApplication) {

    @Provides
    @ApplicationContext
    fun provideApplicationContext(): Context {
        return application
    }

    @Provides
    @RenderingScheduler
    fun provideRenderingScheduler(): Scheduler = AndroidSchedulers.mainThread()

}