package com.metehanbolat.graduateproject.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import com.metehanbolat.graduateproject.R
import com.metehanbolat.graduateproject.viewmodel.UserActivityViewModel

class UserActivity : AppCompatActivity() {

    private lateinit var viewModel : UserActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_GraduateProject)
        setContentView(R.layout.activity_user)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val tempViewModel : UserActivityViewModel by viewModels()
        viewModel = tempViewModel

    }

    override fun onStart() {
        super.onStart()
        viewModel.currentUserControl(this, this)
    }
}