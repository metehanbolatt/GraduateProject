package com.metehanbolat.graduateproject.viewmodel

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.metehanbolat.graduateproject.view.activities.MainActivity
import com.metehanbolat.graduateproject.view.activities.UserActivity

class UserActivityViewModel : ViewModel() {

    private var auth: FirebaseAuth = Firebase.auth

    fun currentUserControl(activity: UserActivity, context : Context){
        val currentUser = auth.currentUser
        if (currentUser != null){
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
            activity.finish()
        }
    }
}