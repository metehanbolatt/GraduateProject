package com.metehanbolat.graduateproject.viewmodel

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.metehanbolat.graduateproject.R
import com.metehanbolat.graduateproject.view.activities.MainActivity

class UserSignInFragmentViewModel : ViewModel() {

    private var auth: FirebaseAuth = Firebase.auth

    fun signIn(activity : FragmentActivity, context : Context, view: View, userEmail : String, userPassword : String){
        if (userEmail.isBlank() || userPassword.isBlank()){
            Snackbar.make(view, context.resources.getString(R.string.please_dont_empty_field), Snackbar.LENGTH_LONG).show()
        }else{
            auth.signInWithEmailAndPassword(userEmail, userPassword).addOnSuccessListener {
                val intent = Intent(context, MainActivity::class.java)
                context.startActivity(intent)
                activity.finish()
            }.addOnFailureListener {
                println(it.localizedMessage)
                when(it.localizedMessage){
                    context.resources.getString(R.string.firebase_badly_email_format) -> Snackbar.make(view, context.resources.getString(R.string.snack_bar_email), Snackbar.LENGTH_LONG).show()
                    context.resources.getString(R.string.firebase_sign_in_password) -> Snackbar.make(view, context.resources.getString(R.string.wrong_password), Snackbar.LENGTH_LONG).show()
                    context.resources.getString(R.string.firebase_sign_in_no_user) -> Snackbar.make(view, context.resources.getString(R.string.no_user), Snackbar.LENGTH_LONG).show()
                    else -> Snackbar.make(view, context.resources.getString(R.string.error_occurred), Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

    fun moveSignUp(v : View){
        Navigation.findNavController(v).navigate(R.id.action_userSignInFragment_to_userSignUpFragment)
    }
}