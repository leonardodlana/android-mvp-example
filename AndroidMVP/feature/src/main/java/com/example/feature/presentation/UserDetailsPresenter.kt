package com.example.feature.presentation

import com.example.feature.domain.UserRepository
import io.reactivex.Scheduler

class UserDetailsPresenter(
    private val userRepository: UserRepository,
    private val renderingScheduler: Scheduler
) : UserDetailsContract.Presenter() {

    private var lastUserIdRequested : String? = null

    override fun onViewAttached() {
        // Setup anything if necessary
    }

    override fun load(userId: String) {
        lastUserIdRequested = userId
        view?.showLoading()
        addDisposable(
            userRepository.getUserDetails(userId)
                .observeOn(renderingScheduler)
                .subscribe { result ->
                    if (result.isSuccess()) {
                        view?.showUserDetails(result.model!!)
                    } else {
                        view?.showError(result.error!!)
                    }
                })
    }

    override fun retry() {
        lastUserIdRequested?.let { userId ->
            load(userId) }
    }
}