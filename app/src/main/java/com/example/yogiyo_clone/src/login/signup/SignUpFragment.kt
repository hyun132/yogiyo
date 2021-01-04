package com.example.yogiyo_clone.src.login.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yogiyo_clone.R
import com.example.yogiyo_clone.config.BaseFragment
import com.example.yogiyo_clone.databinding.FragmentSignUpBinding
import com.softsquared.template.kotlin.src.main.home.models.SignUpResponse

class SignUpFragment : BaseFragment<FragmentSignUpBinding>(FragmentSignUpBinding::bind,R.layout.fragment_sign_up),SignUpFragmentView {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onPostSignUpSuccess(response: SignUpResponse) {
        //회원가입 성공하면
    }

    override fun onPostSignUpFailure(message: String) {
        //회원가입 실패하면
    }

}