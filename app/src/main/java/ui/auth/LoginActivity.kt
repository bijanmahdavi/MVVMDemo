package ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmdemo.R
import com.example.mvvmdemo.databinding.ActivityLoginBinding
import helpers.d
import helpers.toast

class LoginActivity : AppCompatActivity(), AuthListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.authListener = this
    }

    override fun onStarted() {
        //this.toast("OnStarted")
        this.d("LoginActivity","onStarted")

    }

    override fun onSuccess(response: LiveData<String>) {
        response.observe(this, Observer {
            this.d("LoginActivity", "Success! Observer attached to response")
            this.toast("success")
        })
    }

    override fun failure(message: String) {
        this.d("LoginActivity","Login Failed")
        this.toast(message)
    }


}