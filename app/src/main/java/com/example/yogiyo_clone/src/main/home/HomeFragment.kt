package com.example.yogiyo_clone.src.main.home

import android.os.Bundle
import android.view.View
import com.example.yogiyo_clone.R
import com.example.yogiyo_clone.config.BaseFragment
import com.example.yogiyo_clone.databinding.FragmentHomeBinding
import com.softsquared.template.kotlin.src.main.home.models.UserResponse

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home),
    HomeFragmentView {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.homeButtonTryGetJwt.setOnClickListener {
//            showLoadingDialog(context!!)
//            HomeService(this).tryGetUsers() // 서비스에 이 뷰를 넘겨줌. 그러면 서비스에서 요청 처리한뒤
////                                                    이 뷰의 onGetUserSuccess, onGetUserFailure 를 실행함.
//        }
//
//        binding.homeBtnTryPostHttpMethod.setOnClickListener {
//            val email = binding.homeEtId.text.toString()
//            val password = binding.homeEtPw.text.toString()
//            val postRequest = PostSignUpRequest(
//                email = email, password = password,
//                confirmPassword = password, nickname = "test", phoneNumber = "010-0000-0000"
//            )
//            showLoadingDialog(context!!)
//            HomeService(this).tryPostSignUp(postRequest)
//        }
    }

    override fun onGetUserSuccess(response: UserResponse) {
        dismissLoadingDialog()
//        for (User in response.result) {
//            Log.d("HomeFragment", User.toString())
//        }
//        binding.homeButtonTryGetJwt.text = response.message
//        showCustomToast("Get JWT 성공")
    }

    override fun onGetUserFailure(message: String) {
        dismissLoadingDialog()
//        showCustomToast("오류 : $message")
    }

//    override fun onPostSignUpSuccess(response: SignUpResponse) {
//        dismissLoadingDialog()
////        binding.homeBtnTryPostHttpMethod.text = response.message
////        showCustomToast(response.message)
//    }

    override fun onPostSignUpFailure(message: String) {
//        dismissLoadingDialog()
//        showCustomToast("오류 : $message")
    }
}