package data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import data.network.MyApi

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {

    fun login(email:String, password: String): LiveData<String> {
        Log.d("UserRepository login", "Logging in ?")
        val loginResponse = MutableLiveData<String>()
        Log.d("UserRepository loginres", loginResponse.toString())
        MyApi().login(email, password)
            .enqueue(object: Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if(response.isSuccessful){
                        Log.d("suc_response" , response.toString())
                        loginResponse.value = "Login success"
                        //start next activity
                    } else {
                        loginResponse.value = "Account not registered"
                        Log.d("User Repository", "This account does not exist")
                        Log.d("User Repository resp", response.toString())
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.d("fail_response" , t.message!!)
                    loginResponse.value = t.message
                }

            })

        return loginResponse
    }

    fun register(email:String, password: String, name: String, type: String): LiveData<String> {
        Log.d("UserRepository register", "registering ?")
        val registerResponse = MutableLiveData<String>()
        Log.d("UserRepository loginres", registerResponse.toString())
        MyApi().register(email, password, name, type)
            .enqueue(object: Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if(response.isSuccessful){
                        Log.d("suc_response" , response.toString())
                        registerResponse.value = "Register success"
                        //start next activity
                    } else {
                        registerResponse.value = "Account already registered"
                        Log.d("User Repository", "This account already exists")
                        Log.d("User Repository resp", response.toString())
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.d("fail_response" , t.message!!)
                    registerResponse.value = t.message
                }

            })

        return registerResponse
    }
}