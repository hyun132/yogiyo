package com.example.yogiyo_clone.src.main.myyogiyo

import android.os.Bundle
import android.view.View
import com.example.yogiyo_clone.R
import com.example.yogiyo_clone.config.BaseFragment
import com.example.yogiyo_clone.databinding.FragmentHomeBinding
import com.example.yogiyo_clone.src.main.home.HomeFragmentView
import com.softsquared.template.kotlin.src.main.home.models.SignUpResponse
import com.softsquared.template.kotlin.src.main.home.models.UserResponse

class MyYogiyoFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home),
    MyyogiyoFragmentView {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onGetUserInfoSuccess(response: UserResponse) {

    }

    override fun onGetUserInfoFailure(message: String) {

    }

}