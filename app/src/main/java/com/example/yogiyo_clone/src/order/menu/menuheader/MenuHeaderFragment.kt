package com.example.yogiyo_clone.src.order.menu.menuheader

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bumptech.glide.Glide
import com.example.yogiyo_clone.R
import com.example.yogiyo_clone.config.ApplicationClass.Companion.addComma
import com.example.yogiyo_clone.config.ApplicationClass.Companion.currentStore
import com.example.yogiyo_clone.config.BaseFragment
import com.example.yogiyo_clone.databinding.FragmentMenuHeaderBinding
import com.example.yogiyo_clone.src.order.menu.info.RestaurnatInfoFragment
import com.example.yogiyo_clone.src.order.menu.menubottom.BottomMenuFragment
import com.example.yogiyo_clone.src.order.menu.menuheader.model.JjimResponse
import com.example.yogiyo_clone.src.order.menu.menuheader.model.MenuHeaderResponse
import com.example.yogiyo_clone.src.order.menu.review.BottomReviewFragment
import com.google.android.material.tabs.TabLayoutMediator



class MenuHeaderFragment : BaseFragment<FragmentMenuHeaderBinding>(
    FragmentMenuHeaderBinding::bind,
    R.layout.fragment_menu_header
),
    MenuHeaderFragmentView {
    lateinit var myViewPagerAdapter: ViewPagerAdapter
    var storeIdx: Int = 0
    lateinit var fragment: View
    var savenum: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)

//         fragment = inflater.inflate(R.layout.fragment_menu_header, container, false);
        fragment = super.onCreateView(inflater, container, savedInstanceState)!!
//        setHasOptionsMenu(true);

        return fragment;
    }

    override fun onResume() {
        super.onResume()
        activity?.invalidateOptionsMenu();
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getInt("storeIdx")?.let {
            storeIdx = it
            MenuHeaderService(this).tryLogIn(it)
            Log.d("idx : ", it.toString())
        }


        myViewPagerAdapter = ViewPagerAdapter(requireActivity())
        binding.viewpager.adapter = myViewPagerAdapter
//        val tabLayout = view.findViewById<TabLayout>(R.id.menu_tablayout)   //fragment이기 때문에 root view에서 위젯 가져와야함.
        val tabLayoutTextArray = arrayListOf<String>("메뉴", "클린리뷰", "정보")    //tab이름 설정
        TabLayoutMediator(
            binding.tablayout,
            binding.viewpager
        ) { tab, position ->         //tabLayout과 ViewPager를 연결해줌.
            tab.text = tabLayoutTextArray[position]
        }.attach()

        binding.jjimContainer.setOnClickListener {
            //찜 눌렀을 때 처리
            Log.d("jjim : ", "Clicked")
            MenuHeaderService(this).tryJjim(storeIdx)
        }

    }

    @SuppressLint("SetTextI18n")
    override fun onGetMenuHeaderSuccess(response: MenuHeaderResponse) {
        Log.d("MenuHeaderFragment : ", "success")

        binding.standardHeaderToolbar.standardActionTitle.text = response.result.title

        currentStore = response.result

        if (response.result.poster.isNullOrBlank()) {
            binding.restaurantImageview.visibility = View.GONE
        } else {
            val poster = response.result.poster.split(',')
            Glide.with(requireActivity()).load(poster[0]).into(binding.restaurantImageview)
        }

        binding.restaurantNameTextview.text = response.result.title
        val avg: Float = currentStore!!.rateAvg?.toFloat() ?: 0F
        binding.restaurantRatingBar.rating = avg
        if (currentStore!!.rateAvg != null) {
            binding.restaurantReviewRate.text = response.result.rateAvg.toString()
        }

        if (response.result.discountCharge == 0) {
            binding.restaurantDiscountTextview.isGone = true
        } else {
            binding.restaurantDiscountTextview.text =
                "${response.result.discountCharge?.let { addComma(it) }}원 할인"
        }
        binding.restaurantEstimatedTimeTextview.text = "배달예상시간 ${response.result.deliveryTime}"
        binding.minimumOrderPrice.text = "${response.result.limitCharge?.let { addComma(it) }}원"
        binding.payMethod.text = response.result.paymentSystem
        binding.deliveryFee.text = "${response.result.deliveryCharge?.let { addComma(it) }}원"
        binding.chefReply.text = response.result.description?.toString()
        if (response.result.isLike == "TRUE") {
            Glide.with(this).load(R.drawable.ic_jjimed).into(binding.jjimIcon)
        }
        if (response.result.countLike != null) savenum = response.result.countLike

        binding.jjimTextview.text = "찜 ${response.result.countLike}"

    }


    override fun onGetMenuHeaderFailure(message: String) {

    }

    override fun onPostJjimSuccess(response: JjimResponse) {
        if (response.code == 1000) {
            Glide.with(this).load(R.drawable.ic_jjimed).into(binding.jjimIcon)
            binding.jjimTextview.text = "찜 ${(savenum + 1)}"
            savenum += 1
        } else if (response.code == 1001) {
            Glide.with(this).load(R.drawable.ic_jjim).into(binding.jjimIcon)
            binding.jjimTextview.text = "찜 ${(savenum - 1)}"
            savenum -= 1
        }

    }

    override fun onPostJjimFailure(message: String) {

    }

    inner class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int = 3
        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> {
                    val fragment = BottomMenuFragment()
                    fragment.arguments = Bundle().apply {
                        putInt("storeidx", storeIdx)
                    }
                    fragment
                }
                1 -> {
                    val fragment = BottomReviewFragment()
                    fragment.arguments = Bundle().apply {
                        putInt("storeidx", storeIdx)
                    }
                    fragment
                }
                else -> {
                    val fragment = RestaurnatInfoFragment()
                    fragment.arguments = Bundle().apply {
                        putInt("storeidx", storeIdx)
                    }
                    fragment
                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()

        activity?.finish()
    }

}