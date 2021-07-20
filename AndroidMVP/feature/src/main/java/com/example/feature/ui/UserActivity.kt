package com.example.feature.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.feature.R

class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_content, UserDetailsFragment(), "fragment").commit()
    }
}