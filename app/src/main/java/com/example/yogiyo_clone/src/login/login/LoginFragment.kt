package com.example.yogiyo_clone.src.login.login

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.yogiyo_clone.R
import com.example.yogiyo_clone.config.BaseFragment
import com.example.yogiyo_clone.databinding.FragmentLoginBinding
import com.example.yogiyo_clone.src.login.login.model.PostLogInRequest
import com.example.yogiyo_clone.src.login.login.model.LogInResult
import com.example.yogiyo_clone.src.login.signupinfo.SignUpInfoFragment
import com.softsquared.template.kotlin.src.main.home.models.SignUpResponse


class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::bind, R.layout.fragment_login),
        LoginFragmentView {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

    override fun onPostLogInSuccess(logInResult:LogInResult) {
        val token=logInResult.jwt
        val pref = requireActivity().getSharedPreferences("LOGIN_JSX_TOKEN", Application.MODE_PRIVATE)
        pref.edit().putString("LOGIN_JSX_TOKEN",token).apply()
        Log.d("LoginFragment","로그인성공")
        Log.d("sharedPreference",pref.toString())
        Toast.makeText(context, "로그인성공", Toast.LENGTH_SHORT).show()
    }

    override fun onPostLogInFailure(message: String) {
        Toast.makeText(context, "로그인을 해주세요", Toast.LENGTH_SHORT).show()
    }

    override fun onPostSignUpSuccess(response: SignUpResponse) {
        TODO("Not yet implemented")
    }

    override fun onPostSignUpFailure(message: String) {
        TODO("Not yet implemented")
    }

}