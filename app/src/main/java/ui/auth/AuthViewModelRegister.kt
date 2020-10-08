package ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import data.repositories.UserRepository

class AuthViewModelRegister : ViewModel(){

    var email: String? = null
    var password: String? = null
    var name: String? = null
    var type: String? = null

    var authListener: AuthListener? = null

    fun onRegisterButtonClick(view: View){
        authListener?.onStarted()
        if(email.isNullOrEmpty() || password.isNullOrEmpty() || name.isNullOrEmpty() || type.isNullOrEmpty()){
            // failure
            authListener?.failure("failure")
            return
        }
        // success

        var loginResponse = UserRepository().register(email!!, password!!, name!!, type!!)
        authListener?.onSuccess(loginResponse)
    }

}