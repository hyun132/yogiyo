package com.example.yogiyo_clone.src.login.login

import android.annotation.SuppressLint
import android.app.Application
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.yogiyo_clone.R
import com.example.yogiyo_clone.config.ApplicationClass.Companion.SAVE_TOKEN
import com.example.yogiyo_clone.config.ApplicationClass.Companion.X_ACCESS_TOKEN
import com.example.yogiyo_clone.config.ApplicationClass.Companion.sSharedPreferences
import com.example.yogiyo_clone.config.BaseFragment
import com.example.yogiyo_clone.databinding.FragmentLoginBinding
import com.example.yogiyo_clone.src.login.login.model.LogInResponse
import com.example.yogiyo_clone.src.login.login.model.PostLogInRequest
import com.example.yogiyo_clone.src.login.login.model.LogInResult
import com.example.yogiyo_clone.src.login.signupinfo.SignUpInfoFragment
import com.example.yogiyo_clone.src.main.MainActivity


class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::bind, R.layout.fragment_login),
        LoginFragmentView {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginPasswordTextview.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            @SuppressLint("ResourceAsColor")
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                context?.getResources()?.let { binding.loginButton.setBackgroundColor(it.getColor(R.color.signature)) };

            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        binding.loginButton.setOnClickListener {
            var email=binding.loginEmailTextview.text.toString()
            var pw=binding.loginPasswordTextview.text.toString()
            if(email.isNotEmpty() || pw.isNotEmpty()){
                val loginRequest=PostLogInRequest(email,pw)
                LoginService(this).tryLogIn(loginRequest)
                Log.d("LoginButton Clicked","$email $pw")
            }
        }

        binding.loginSignupBtn.setOnClickListener {
            val newFragment = SignUpInfoFragment()
            val transaction = requireActivity().supportFragmentManager.beginTransaction().apply {
                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack so the user can navigate back
                replace(R.id.login_frame, newFragment)
                addToBackStack(null)
            }
            // Commit the transaction
            transaction.commit();
        }



    }

    override fun onPostLogInSuccess(response:LogInResponse) {
        var logInResult = response.result
        val token= logInResult?.jwt
//        val pref = requireActivity().getSharedPreferences(X_ACCESS_TOKEN, Application.MODE_PRIVATE)
//        pref.edit().putString(X_ACCESS_TOKEN,token).apply()

        if (token != null) {
            Log.d("sharedPreference",token)
        }

        if (response.code==1000){
            var intent = Intent(this.activity,MainActivity::class.java)
            startActivity(intent)
            Toast.makeText(context, "로그인성공", Toast.LENGTH_SHORT).show()
            sSharedPreferences.edit().putString(X_ACCESS_TOKEN,token).apply()
            if(binding.autoLoginCheckbox.isChecked){
                SAVE_TOKEN=true
            }
        }else showCustomToast("아이디 비밀번호를 올바르게 입력해주세요")



    }

    override fun onPostLogInFailure(message: String) {
        Toast.makeText(context, "로그인을 해주세요", Toast.LENGTH_SHORT).show()
    }


}