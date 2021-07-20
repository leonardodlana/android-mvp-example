package com.example.androidmvp

import com.example.androidmvp.di.AndroidModule
import com.example.androidmvp.di.ApplicationComponent
import com.example.androidmvp.di.DaggerApplicationComponent
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MyApplication : DaggerApplication() {

    private lateinit var dependencyGraph: ApplicationComponent

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        dependencyGraph = DaggerApplicationComponent.builder()
            .androidModule(AndroidModule(this))
            .build()

        return dependencyGraph
    }
}