package com.example.androidmvp.di

import android.content.Context
import com.example.androidmvp.providers.AndroidURLProvider
import com.example.common.BaseURLProvider
import com.example.common.annotations.ApplicationContext
import com.example.feature.domain.UserApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    fun provideBaseUrlProvider(@ApplicationContext context: Context): BaseURLProvider {
        return AndroidURLProvider(context)
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, baseUrl: BaseURLProvider) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl.getBaseURL())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideDashboardApi(retrofit: Retrofit): UserApi =
        retrofit.create(UserApi::class.java)


}