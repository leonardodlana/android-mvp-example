package com.example.feature.presentation

import com.example.common.BasePresenter
import com.example.common.BaseView
import com.example.feature.domain.model.UserDetails

interface UserDetailsContract {

    interface View : BaseView {
        fun showUserDetails(userDetails: UserDetails)
    }

    abstract class Presenter : BasePresenter<View>() {
        abstract fun load(userId: String)
        abstract fun retry()
    }

}