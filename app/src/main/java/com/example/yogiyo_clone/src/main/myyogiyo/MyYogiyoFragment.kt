package com.example.yogiyo_clone.src.main.myyogiyo

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.yogiyo_clone.R
import com.example.yogiyo_clone.config.ApplicationClass.Companion.SAVE_TOKEN
import com.example.yogiyo_clone.config.BaseFragment
import com.example.yogiyo_clone.databinding.FragmentMyYogiyoBinding
import com.example.yogiyo_clone.src.login.LoginActivity
import com.example.yogiyo_clone.src.main.myyogiyo.models.UserResponse

class MyYogiyoFragment : BaseFragment<FragmentMyYogiyoBinding>(FragmentMyYogiyoBinding::bind, R.layout.fragment_my_yogiyo),
    MyyogiyoFragmentView {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            val intent=Intent(activity,LoginActivity::class.java)
            intent.putExtra("screen",0)
            startActivity(intent)
        }
        binding.signupButton.setOnClickListener {
            val intent=Intent(activity,LoginActivity::class.java)
            intent.putExtra("screen",1)
            startActivity(intent)
        }
        //after_user_info_area
        if(SAVE_TOKEN==true){
            binding.afterUserInfoArea.visibility=View.VISIBLE
            binding.cuponNumberTextview.text="0장"
            binding.pointNumberTextview.text="0원"
            binding.reviewNumberTextview.text="0건"
        }

    }

    override fun onGetUserInfoSuccess(response: UserResponse) {
    }

    override fun onGetUserInfoFailure(message: String) {
    }


}