package ui.auth

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import data.repositories.UserRepository

class AuthViewModel : ViewModel(){

    var email: String? = null
    var password: String? = null

    var authListener: AuthListener? = null

    fun onLoginButtonClick(view: View){
        authListener?.onStarted()
        if(email.isNullOrEmpty() || password.isNullOrEmpty()){
            // failure
            authListener?.failure("failure")
            return
        }
        // success

        var loginResponse = UserRepository().login(email!!, password!!)
        authListener?.onSuccess(loginResponse)
    }

}