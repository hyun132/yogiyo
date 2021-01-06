package com.example.yogiyo_clone.src.login.phoneconfirm

import android.util.Log
import com.example.yogiyo_clone.config.ApplicationClass
import com.example.yogiyo_clone.src.login.phoneconfirm.model.PhoneAuthResponse
import com.example.yogiyo_clone.src.login.phoneconfirm.model.PostSignUpRequest
import com.example.yogiyo_clone.src.login.phoneconfirm.model.PostphoneAuthRequest
import com.example.yogiyo_clone.src.login.phoneconfirm.model.SignUpResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhoneConfirmService(val view: PhoneConfirmFragment) { // 얘는 이전에 repository랑 비슷한 역할

    fun trySendSms(postphoneAuthRequest: PostphoneAuthRequest){
        val phoneAuthRetrofitInterface = ApplicationClass.sRetrofit.create(PhoneAuthRetrofitInterface::class.java)
        phoneAuthRetrofitInterface.sendSms(postphoneAuthRequest).enqueue(object : Callback<PhoneAuthResponse>{
            override fun onResponse(
                call: Call<PhoneAuthResponse>,
                response: Response<PhoneAuthResponse>
            ) {
                response.body()?.let { view.onPostSendSmsSuccess(it) }
                Log.d("trySendSms : ", " success")
            }

            override fun onFailure(call: Call<PhoneAuthResponse>, t: Throwable) {
                t.message?.let { view.onPostSendSmsFailure(it) }
                Log.d("trySendSms : ", " fail")
            }

        })
    }


    fun trySignUp(postSignUpRequest: PostSignUpRequest){
        val signUpRetrofitInterface = ApplicationClass.sRetrofit.create(PhoneAuthRetrofitInterface::class.java)
        signUpRetrofitInterface.signUp(postSignUpRequest).enqueue(object :Callback<SignUpResponse>{
            override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
                response.body()?.let { view.onPostSignUpSuccess(it) }
            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                t.message?.let { view.onPostSignUpFailure(it) }
            }

        })
    }

}
