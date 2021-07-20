package com.example.common

import io.reactivex.Completable
import io.reactivex.Maybe

interface LocalCache {
    fun put(key: String, value: Any): Completable
    fun get(key: String): Maybe<Any>
    fun clearAll(): Completable
}