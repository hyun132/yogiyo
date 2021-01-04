package com.example.yogiyo_clone.src.login

import android.os.Bundle
import com.example.yogiyo_clone.R
import com.example.yogiyo_clone.config.BaseActivity
import com.example.yogiyo_clone.databinding.ActivityLoginBinding
import com.example.yogiyo_clone.src.login.login.LoginFragment
import com.example.yogiyo_clone.src.login.signupinfo.SignUpInfoFragment

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create fragment and give it an argument specifying the article it should show
        val loginFragment = LoginFragment()
        val signupFragment = SignUpInfoFragment()

        val transaction = supportFragmentManager.beginTransaction()

        val page = intent.getIntExtra("screen",0)
        when(page){
            0-> {
                transaction.replace(R.id.login_frame, loginFragment)
                transaction.addToBackStack(null)
            }
            1->{
                transaction.replace(R.id.login_frame, signupFragment)
                transaction.addToBackStack(null)
            }
        }

//        val args = Bundle()
//        newFragment.arguments = args

//        val transaction = supportFragmentManager.beginTransaction().apply {
//            // Replace whatever is in the fragment_container view with this fragment,
//            // and add the transaction to the back stack so the user can navigate back
//            replace(R.id.login_frame, newFragment)
//            addToBackStack(null)
//        }

        // Commit the transaction
        transaction.commit();

        actionBar?.hide()
    }

}