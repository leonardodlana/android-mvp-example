package com.example.androidmvp.providers

import android.content.Context
import com.example.androidmvp.R
import com.example.common.BaseURLProvider

class AndroidURLProvider(private val context: Context) : BaseURLProvider {
    override fun getBaseURL(): String {
        return context.resources.getString(R.string.baseURL)
    }
}