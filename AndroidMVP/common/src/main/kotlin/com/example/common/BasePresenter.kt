package com.example.common

import io.reactivex.disposables.Disposable
import java.lang.ref.WeakReference

abstract class BasePresenter<V : BaseView> {

    private val disposableList = ArrayList<WeakReference<Disposable>>()
    protected var view : V? = null

    fun attachView(view: V) {
        this.view = view
        onViewAttached()
    }

    fun detachView() {
        view = null
        onViewDetached()
        clearDisposables()
    }

    protected fun clearDisposables() {
        disposableList.forEach {
            it.get()?.dispose()
        }

        disposableList.clear()
    }

    protected fun addDisposable(disposable: Disposable) {
        disposableList.add(WeakReference(disposable))
    }

    protected fun <T : Disposable> T.track() = this.also{ addDisposable(it) }

    protected abstract fun onViewAttached()

    protected open fun onViewDetached() { /* Override in children when required */}

}