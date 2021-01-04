package com.example.yogiyo_clone.src.login.signupinfo

import android.util.Log
import com.example.yogiyo_clone.config.ApplicationClass
import com.example.yogiyo_clone.src.login.signupinfo.model.PostUserValicationResponse
import com.example.yogiyo_clone.src.login.signupinfo.model.PostUserValidationRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpInfoService(val view: SignUpInfoFragment) { // 얘는 이전에 repository랑 비슷한 역할

    fun tryGetUserValidation(postUserValicationResponse:PostUserValidationRequest){
        val signUpInfoRetrofitInterface = ApplicationClass.sRetrofit.create(SignUpInfoRetrofitInterface::class.java)
        signUpInfoRetrofitInterface.getUserValidation(postUserValicationResponse).enqueue(object : Callback<PostUserValicationResponse>{
            override fun onResponse(
                call: Call<PostUserValicationResponse>,
                response: Response<PostUserValicationResponse>
            ) {
                response.body()!!.let { view.onPostUserValidationSuccess(it) }
                Log.d("tryGetUserValidation : ", " onResponse ")
            }

            override fun onFailure(call: Call<PostUserValicationResponse>, t: Throwable) {
                view.onPostUserValidationFailure(t.message.toString())
                Log.d("tryGetUserValidation : ", " fail ")
            }

        })
    }

}
