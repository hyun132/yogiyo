package com.example.yogiyo_clone.src.login.login

import com.example.yogiyo_clone.src.login.login.model.LogInResponse
import com.example.yogiyo_clone.src.login.login.model.PostLogInRequest
import retrofit2.Call
import retrofit2.http.*

interface LoginRetrofitInterface {

    //
    @POST("/user-login")
    fun logIn(@Body params: PostLogInRequest) : Call<LogInResponse>


}
