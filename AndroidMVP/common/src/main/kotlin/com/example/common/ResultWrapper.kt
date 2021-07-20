package com.example.common

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

/**
 * Generic result wrapper
 */
data class ResultWrapper<DataModel>(val model: DataModel?, val error: DataError? = null) {
    fun isSuccess(): Boolean = model != null && error == null
}


/**
 * Extensions
 **/

fun <DataModel : Any> Single<DataModel>.toResult(scheduler: Scheduler = Schedulers.io()): Observable<ResultWrapper<DataModel>> {
    return this.map { response ->
        // Success
        ResultWrapper(response, null)
    }.onErrorReturn { error ->
        // Handle different errors if necessary i.e. parse 'error'
        ResultWrapper(null, DataError.GENERIC_ERROR)
    }
        .subscribeOn(scheduler)
        .unsubscribeOn(scheduler)
        .toObservable()
}