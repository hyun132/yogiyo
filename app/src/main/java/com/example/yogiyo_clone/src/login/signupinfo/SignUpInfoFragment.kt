package com.example.yogiyo_clone.src.login.signupinfo

import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.yogiyo_clone.R
import com.example.yogiyo_clone.config.BaseFragment
import com.example.yogiyo_clone.databinding.FragmentSignUpInfoBinding
import com.example.yogiyo_clone.src.login.login.LoginService
import com.example.yogiyo_clone.src.login.login.model.PostLogInRequest
import com.example.yogiyo_clone.src.login.signupinfo.model.PostUserValicationResponse
import com.example.yogiyo_clone.src.login.signupinfo.model.PostUserValidationRequest
import com.softsquared.template.kotlin.src.main.home.models.SignUpResponse

class SignUpInfoFragment : BaseFragment<FragmentSignUpInfoBinding>(FragmentSignUpInfoBinding::bind,R.layout.fragment_sign_up_info),SignUpInfoFragmentView {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signupInfoNextButton.setOnClickListener {
            var email=binding.signupEmailTextview.text.toString()
            var pw=binding.signupPasswordTextview.text.toString()
            var checkPw=binding.signupConfirmPasswordTextview.text.toString()
//            var nickname=binding.signupNicknameTextview.text.toString()
            if(email.isNotEmpty() || pw.isNotEmpty() || checkPw.isNotEmpty()){
                val userValidationRequest= PostUserValidationRequest(email,pw,checkPw)
                SignUpInfoService(this).tryGetUserValidation(userValidationRequest)
                Log.d("LoginButton Clicked","$email $pw")
            }
        }

    }

    override fun onPostUserValidationSuccess(response: PostUserValicationResponse) {
        Log.d("SignUpInfoInfoFragment:"," success")
    }

    override fun onPostUserValidationFailure(message: String) {
        Log.d("SignUpInfoInfoFragment:"," fail")
    }


}