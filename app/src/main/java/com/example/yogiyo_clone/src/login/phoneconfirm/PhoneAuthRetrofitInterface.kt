package com.example.yogiyo_clone.src.login.phoneconfirm

import com.example.yogiyo_clone.src.login.login.model.PostLogInRequest
import com.example.yogiyo_clone.src.login.phoneconfirm.model.PhoneAuthResponse
import com.example.yogiyo_clone.src.login.phoneconfirm.model.PostSignUpRequest
import com.example.yogiyo_clone.src.login.phoneconfirm.model.PostphoneAuthRequest
import com.example.yogiyo_clone.src.login.phoneconfirm.model.SignUpResponse
import retrofit2.Call
import retrofit2.http.*

interface PhoneAuthRetrofitInterface {

    //
    @POST("/user/sms")
    fun sendSms(@Body params: PostphoneAuthRequest) : Call<PhoneAuthResponse>

    @POST("/user-join")
    fun signUp(@Body params: PostSignUpRequest) : Call<SignUpResponse>


}
