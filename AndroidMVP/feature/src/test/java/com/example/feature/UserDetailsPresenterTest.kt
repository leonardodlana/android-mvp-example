package com.example.feature

import com.example.common.DataError
import com.example.common.ResultWrapper
import com.example.feature.domain.UserRepository
import com.example.feature.domain.model.UserDetails
import com.example.feature.presentation.UserDetailsContract
import com.example.feature.presentation.UserDetailsPresenter
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class UserDetailsPresenterTest {

    companion object {

        private val USER_DETAILS_TEST_DATA = UserDetails(2, "leonardo@gmail.com", "Leonardo", "Duarte", "https://m.media-amazon.com/images/M/MV5BZmZhNWMyODgtMzA0OC00NWFhLTllODQtYmJkZjYxYWU4MGU1XkEyXkFqcGdeQWFybm8@._V1_.jpg")

    }

    @Mock
    lateinit var mockView: UserDetailsContract.View

    @Mock
    lateinit var mockRepository: UserRepository

    lateinit var presenter: UserDetailsContract.Presenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = UserDetailsPresenter(
            mockRepository,
            Schedulers.trampoline())

        presenter.attachView(mockView)
    }

    @Test
    fun `I should see loading when the view calls load`() {
        givenDataWillReturn()

        presenter.load("2")

        verify(mockView).showLoading()
    }

    @Test
    fun `I should see the user details when the request succeeds`() {
        givenDataWillReturn()

        presenter.load("2")

        verify(mockView).showUserDetails(USER_DETAILS_TEST_DATA)
    }

    @Test
    fun `I should see the error when the request fails`() {
        givenErrorWillReturn()

        presenter.load("2")

        verify(mockView).showError(DataError.GENERIC_ERROR)
    }

    private fun givenDataWillReturn() {
        whenever(mockRepository.getUserDetails("2")).thenReturn(Observable.just(ResultWrapper(USER_DETAILS_TEST_DATA)))
    }

    private fun givenErrorWillReturn() {
        whenever(mockRepository.getUserDetails("2")).thenReturn(Observable.just(ResultWrapper<UserDetails>(null, DataError.GENERIC_ERROR)))
    }

}