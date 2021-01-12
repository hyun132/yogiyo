package com.example.yogiyo_clone.src.order.menuheader

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bumptech.glide.Glide
import com.example.yogiyo_clone.R
import com.example.yogiyo_clone.config.BaseFragment
import com.example.yogiyo_clone.databinding.FragmentMenuHeaderBinding
import com.example.yogiyo_clone.src.order.menubottom.DetailFragment
import com.example.yogiyo_clone.src.order.menuheader.model.MenuHeaderResponse
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.text.DecimalFormat


class MenuHeaderFragment : BaseFragment<FragmentMenuHeaderBinding>(FragmentMenuHeaderBinding::bind,
    R.layout.fragment_menu_header),
    MenuHeaderFragmentView {
    lateinit var myViewPagerAdapter:ViewPagerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        var idx = arguments?.get("idx")
        var idx =1

        MenuHeaderService(this).tryLogIn(idx)

        myViewPagerAdapter = ViewPagerAdapter(requireActivity())
        binding.viewpager.adapter = myViewPagerAdapter
//        val tabLayout = view.findViewById<TabLayout>(R.id.menu_tablayout)   //fragment이기 때문에 root view에서 위젯 가져와야함.
        val tabLayoutTextArray= arrayListOf<String>("메뉴","클린리뷰","정보")    //tab이름 설정
        TabLayoutMediator(binding.tablayout, binding.viewpager) { tab, position ->         //tabLayout과 ViewPager를 연결해줌.
            tab.text=tabLayoutTextArray[position]
        }.attach()

    }


    @SuppressLint("SetTextI18n")
    override fun onGetMenuHeaderSuccess(response: MenuHeaderResponse) {
        Log.d("MenuHeaderFragment : ", "success")

        val poster =response.result.poster.split('|')
        Glide.with(requireActivity()).load(poster[0]).into(binding.restaurantImageview)
        binding.restaurantNameTextview.text = response.result.title
        binding.restaurantRatingBar.rating= response.result.rateAvg.toFloat()
        binding.restaurantReviewRate.text=response.result.rateAvg.toString()
        if(response.result.discountCharge==0){
            binding.restaurantDiscountTextview.isGone=true
        }else{
            binding.restaurantDiscountTextview.text="${addComma(response.result.discountCharge)}원 할인"
        }
        binding.restaurantEstimatedTimeTextview.text="배달예상시간 ${response.result.deliveryTime}"
        binding.minimumOrderPrice.text="${addComma(response.result.limitCharge)}원"
        binding.payMethod.text=response.result.paymentSystem
        binding.deliveryFee.text="${addComma(response.result.deliveryCharge)}원"
        binding.chefNotice.text= response.result.description?.toString()
        binding.jjimTextview.text="찜 ${response.result.countLike}"

        binding.shareButton.setOnClickListener {
            //찜 눌렀을 때 처리
        }
    }

    fun addComma(price:Int):String{
        val dec = DecimalFormat("#,###")
        return dec.format(price)
    }

    override fun onGetMenuHeaderFailure(message: String) {

    }

    class ViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int =3
        override fun createFragment(position: Int): Fragment {
            return when(position){
                0->DetailFragment()
//                1->NewFragment()  //리뷰
//                2->BestFragment()  // 정보
                else->DetailFragment()
            }
        }

    }

}