package com.example.common

import io.reactivex.Completable
import io.reactivex.Maybe
import java.util.*

class LocalCacheImpl() : LocalCache {

    private val cacheMap : MutableMap<String, Any> by lazy { HashMap<String, Any>() }

    override fun put(key: String, value: Any): Completable {
        return Completable.fromAction {
            cacheMap[key] = value
        }
    }

    override fun get(key: String): Maybe<Any> {
        return Maybe.defer {
            cacheMap[key]?.let { Maybe.just(it) } ?: Maybe.empty()
        }
    }

    override fun clearAll(): Completable {
        return Completable.fromAction {
            cacheMap.clear()
        }
    }

}