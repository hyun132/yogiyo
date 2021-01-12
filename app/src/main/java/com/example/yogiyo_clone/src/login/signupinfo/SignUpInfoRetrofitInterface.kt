package com.example.yogiyo_clone.src.login.signupinfo

import com.example.yogiyo_clone.src.login.login.model.LogInResponse
import com.example.yogiyo_clone.src.login.login.model.PostLogInRequest
import com.example.yogiyo_clone.src.login.signupinfo.model.PostUserValicationResponse
import com.example.yogiyo_clone.src.login.signupinfo.model.PostUserValidationRequest
import retrofit2.Call
import retrofit2.http.*

interface SignUpInfoRetrofitInterface {


    @POST("/valid-userinfo")
    fun getUserValidation(@Body params: PostUserValidationRequest) : Call<PostUserValicationResponse>


}
