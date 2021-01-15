package com.example.yogiyo_clone.src.login.login

import android.util.Log
import com.example.yogiyo_clone.config.ApplicationClass
import com.example.yogiyo_clone.src.login.login.model.LogInResponse
import com.example.yogiyo_clone.src.login.login.model.PostLogInRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginService(val view: LoginFragment) { // 얘는 이전에 repository랑 비슷한 역할

    fun tryLogIn(postLogInRequest:PostLogInRequest){
        val homeRetrofitInterface = ApplicationClass.sRetrofit.create(LoginRetrofitInterface::class.java)
        homeRetrofitInterface.logIn(postLogInRequest).enqueue(object : Callback<LogInResponse>{
            override fun onResponse(call: Call<LogInResponse>, response: Response<LogInResponse>) {
                response.body()?.let { view.onPostLogInSuccess(it) }
                Log.d("onResponse", response.body()?.result?.jwt.toString())
            }

            override fun onFailure(call: Call<LogInResponse>, t: Throwable) {
                Log.d("onResponse", call.toString())
            }

        })
    }

}
