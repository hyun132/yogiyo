package com.example.yogiyo_clone.src.login.phoneconfirm

import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.yogiyo_clone.R
import com.example.yogiyo_clone.config.BaseFragment
import com.example.yogiyo_clone.databinding.FragmentPhoneConfirmBinding
import com.example.yogiyo_clone.src.login.login.LoginFragment
import com.example.yogiyo_clone.src.login.phoneconfirm.model.PhoneAuthResponse
import com.example.yogiyo_clone.src.login.phoneconfirm.model.PostSignUpRequest
import com.example.yogiyo_clone.src.login.phoneconfirm.model.PostphoneAuthRequest
import com.example.yogiyo_clone.src.login.phoneconfirm.model.SignUpResponse
import com.example.yogiyo_clone.src.login.signupinfo.SignUpInfoFragment

class PhoneConfirmFragment : BaseFragment<FragmentPhoneConfirmBinding>(FragmentPhoneConfirmBinding::bind, R.layout.fragment_phone_confirm),
        PhoneAuthFragmentView {

//    var email:String by lazy { requireArguments().getString("email") }
    var email:String=""
    var password:String=""
    var nickname:String=""
    var push:Int=0
    lateinit var phoneNumber:String
    var authNumber:Int? = null

    var isSmsSended=false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var userInfo = this.arguments

        if (userInfo != null) {
            email= userInfo.getString("email").toString()
            password= userInfo.getString("pw").toString()
            nickname= userInfo.getString("nickname").toString()
            push= userInfo.getInt("push")
        }



        binding.sendSmsButton.setOnClickListener {
            if(binding.phoneNumberTextview.text.isNotBlank()){
                phoneNumber=binding.phoneNumberTextview.text.toString()
                val postphoneAuthRequest=PostphoneAuthRequest(phoneNumber)
                PhoneConfirmService(this).trySendSms(postphoneAuthRequest)
            }
        }

        binding.phoneConfirmSignupButton.setOnClickListener {
            Log.d("SignupButton Clicked: "," ${binding.phoneCodeTextview.text}")
            Log.d("LoginButton Clicked","$email $password")
            if (isSmsSended && binding.phoneCodeTextview.text.isNotEmpty()){
                authNumber=binding.phoneCodeTextview.text.toString().toInt()
                val signupRequest=PostSignUpRequest(email,password,nickname,push,phoneNumber,authNumber!!)
                PhoneConfirmService(this).trySignUp(signupRequest)
            }
            else showCustomToast("핸드폰 인증을 먼저 해주세요")
        }



    }

    override fun onPostSendSmsSuccess(phoneAuthResponse: PhoneAuthResponse) {
        //여기서 모든 정보 보내서 회원가입하는 부분 작성해야함.
        isSmsSended=true
        Log.d("PhoneConfirmFragment: "," 핸드폰 인증 성공!")
    }

    override fun onPostSendSmsFailure(message: String) {
        Log.d("PhoneConfirmFragment: "," 핸드폰 인증 실패")
    }

    override fun onPostSignUpSuccess(signUpResponse: SignUpResponse) {
        Log.d("PhoneConfirmFragment: "," 회원가입 성공!")

        val newFragment = LoginFragment()

        val transaction = requireActivity().supportFragmentManager.beginTransaction().apply {
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            replace(R.id.login_frame, newFragment)
            addToBackStack(null)
        }
        // Commit the transaction
        transaction.commit();
    }

    override fun onPostSignUpFailure(message: String) {
        Log.d("PhoneConfirmFragment: "," 회원가입 실패")
    }

}