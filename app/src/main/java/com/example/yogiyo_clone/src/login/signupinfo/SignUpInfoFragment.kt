package com.example.yogiyo_clone.src.login.signupinfo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.yogiyo_clone.R
import com.example.yogiyo_clone.config.BaseFragment
import com.example.yogiyo_clone.databinding.FragmentSignUpInfoBinding
import com.example.yogiyo_clone.src.login.phoneconfirm.PhoneConfirmFragment
import com.example.yogiyo_clone.src.login.signupinfo.model.PostUserValicationResponse
import com.example.yogiyo_clone.src.login.signupinfo.model.PostUserValidationRequest
import com.example.yogiyo_clone.src.main.MainActivity

class SignUpInfoFragment : BaseFragment<FragmentSignUpInfoBinding>(
    FragmentSignUpInfoBinding::bind,
    R.layout.fragment_sign_up_info
), SignUpInfoFragmentView {

    lateinit var email: String
    lateinit var pw: String
    lateinit var checkPw: String
    var push: Int = 0
    lateinit var nickname: String
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.signupInfoNextButton.setOnClickListener {

            if (binding.pushAlertAgreementCheckbox.isChecked) {
                push = 1
            }

//            binding.signupEmailTextview.

            email = binding.signupEmailTextview.text.toString()
            pw = binding.signupPasswordTextview.text.toString()
            checkPw = binding.signupConfirmPasswordTextview.text.toString()
            nickname = binding.signupNicknameTextview.text.toString()
            Log.d("SignUpInfoInfoFragment:", " $email, $pw $checkPw, $nickname")
            if (email.isNotEmpty() || pw.isNotEmpty() || checkPw.isNotEmpty()) {
                val userValidationRequest = PostUserValidationRequest(email, pw, checkPw)
                SignUpInfoService(this).tryGetUserValidation(userValidationRequest)
                Log.d("LoginButton Clicked", "$email $pw")
            }
        }

    }

    override fun onPostUserValidationSuccess(response: PostUserValicationResponse) {
        Log.d("SignUpInfoInfoFragment:", " success")

        if (response.code == 1000) {
            val newFragment = PhoneConfirmFragment()
            val transaction = requireActivity().supportFragmentManager.beginTransaction().apply {
                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack so the user can navigate back
                arguments = Bundle().apply {
                    putString("email", email)
                    putString("pw", pw)
                    putString("nickname", nickname)
                    putInt("push", push)
                }

                newFragment.arguments = arguments
                replace(R.id.login_frame, newFragment)
                addToBackStack(null)
            }
            // Commit the transaction
            transaction.commit();

        } else showCustomToast("입력한 정보가 알맞지 않습니다.")

    }

    override fun onPostUserValidationFailure(message: String) {
        Log.d("SignUpInfoInfoFragment:", " fail")
    }


}