package com.example.feature.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import com.example.common.DataError
import com.example.feature.R
import com.example.feature.domain.model.UserDetails
import com.example.feature.presentation.UserDetailsContract
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class UserDetailsFragment : DaggerFragment(), UserDetailsContract.View {

    // Could be generic to all views or custom for this one
    enum class VIEW_STATE {
        SUCCESS, LOADING, ERROR
    }

    @Inject
    lateinit var presenter: UserDetailsContract.Presenter

    private lateinit var contentLayout : View
    private lateinit var loadingView : ProgressBar
    private lateinit var retryButton : Button
    private lateinit var emailValueTextView : TextView
    private lateinit var firstNameValueTextView : TextView
    private lateinit var lastNameValueTextView : TextView
    private lateinit var avatarImageView : ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Could be replace with view binding
        contentLayout = view.findViewById(R.id.content_layout)
        loadingView = view.findViewById(R.id.loading_spinner)
        retryButton = view.findViewById(R.id.retry_button)

        emailValueTextView = view.findViewById(R.id.email_value)
        firstNameValueTextView = view.findViewById(R.id.first_name_value)
        lastNameValueTextView = view.findViewById(R.id.last_name_value)
        avatarImageView = view.findViewById(R.id.avatar_image)

        presenter.attachView(this)
        presenter.load("2") // Hardcoded for now

        retryButton.setOnClickListener { presenter.retry() }
    }

    override fun showUserDetails(userDetails: UserDetails) {
        setViewState(VIEW_STATE.SUCCESS)
        emailValueTextView.text = userDetails.email
        firstNameValueTextView.text = userDetails.firstName
        lastNameValueTextView.text = userDetails.lastName
        Glide.with(avatarImageView).load(userDetails.avatarURL).into(avatarImageView)
    }

    override fun showLoading() {
        setViewState(VIEW_STATE.LOADING)
    }

    override fun showError(error: DataError) {
        setViewState(VIEW_STATE.ERROR)
        context?.let { Toast.makeText(context, R.string.generic_error, Toast.LENGTH_SHORT).show() }
    }

    private fun setViewState(viewState: VIEW_STATE) {
        when(viewState) {
            VIEW_STATE.SUCCESS -> {
                loadingView.visibility = View.GONE
                contentLayout.visibility = View.VISIBLE
                retryButton.visibility = View.GONE
            }
            VIEW_STATE.LOADING -> {
                contentLayout.visibility = View.INVISIBLE
                loadingView.visibility = View.VISIBLE
                retryButton.visibility = View.INVISIBLE
            }
            VIEW_STATE.ERROR -> {
                retryButton.visibility = View.VISIBLE
                contentLayout.visibility = View.INVISIBLE
                loadingView.visibility = View.INVISIBLE
            }
        }
    }
}