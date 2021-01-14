package com.example.yogiyo_clone.src.order.menu.menuheader

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
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
import java.text.DecimalFormat


class MenuHeaderFragment : BaseFragment<FragmentMenuHeaderBinding>(FragmentMenuHeaderBinding::bind,
    R.layout.fragment_menu_header),
    MenuHeaderFragmentView {
    lateinit var myViewPagerAdapter:ViewPagerAdapter
    var storeIdx:Int=0
    lateinit var fragment: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
         super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_menu, menu);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.action_search -> Log.d("actionbar","search icon clicked")
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)

//         fragment = inflater.inflate(R.layout.fragment_menu_header, container, false);
        fragment = super.onCreateView(inflater, container, savedInstanceState)!!
        setHasOptionsMenu(true);

        return fragment;
    }

    override fun onResume() {
        super.onResume()
        activity?.invalidateOptionsMenu();
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getInt("storeIdx")?.let {
            storeIdx=it
            MenuHeaderService(this).tryLogIn(it)
            Log.d("idx : ", it.toString())
        }


        myViewPagerAdapter = ViewPagerAdapter(requireActivity())
        binding.viewpager.adapter = myViewPagerAdapter
//        val tabLayout = view.findViewById<TabLayout>(R.id.menu_tablayout)   //fragment이기 때문에 root view에서 위젯 가져와야함.
        val tabLayoutTextArray= arrayListOf<String>("메뉴","클린리뷰","정보")    //tab이름 설정
        TabLayoutMediator(binding.tablayout, binding.viewpager) { tab, position ->         //tabLayout과 ViewPager를 연결해줌.
            tab.text=tabLayoutTextArray[position]
        }.attach()

        binding.jjimTextview.setOnClickListener {
            //찜 눌렀을 때 처리
            Log.d("jjim : ","Clicked")
            MenuHeaderService(this).tryJjim(storeIdx)
        }

    }

    @SuppressLint("SetTextI18n")
    override fun onGetMenuHeaderSuccess(response: MenuHeaderResponse) {
        Log.d("MenuHeaderFragment : ", "success")

        currentStore=response.result

        if(response.result.poster.isNullOrBlank()){
            binding.restaurantImageview.visibility=View.GONE
        }else{
            val poster= response.result.poster.split(',')
            Glide.with(requireActivity()).load(poster[0]).into(binding.restaurantImageview)
        }

        activity?.actionBar?.title=response.result.title
//        binding.restaurant.text= response.result.title
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
        binding.chefReply.text= response.result.description?.toString()
        binding.jjimTextview.text="찜 ${response.result.countLike}"

        (activity as AppCompatActivity?)!!.supportActionBar!!.title = response.result.title

    }


    override fun onGetMenuHeaderFailure(message: String) {

    }

    override fun onPostJjimSuccess(response: JjimResponse) {
        Glide.with(this).load(R.drawable.ic_jjimed).into(binding.jjimIcon)
    }

    override fun onPostJjimFailure(message: String) {

    }

    inner class ViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int =3
        override fun createFragment(position: Int): Fragment {
            return when(position){
                0->{
                    val fragment = BottomMenuFragment()
                    fragment.arguments=Bundle().apply {
                        putInt("storeidx",storeIdx)
                    }
                    fragment
                }
                1 -> {
                    val fragment = BottomReviewFragment()
                    fragment.arguments=Bundle().apply {
                        putInt("storeidx",storeIdx) }
                    fragment
                }
                else->{
                    val fragment = RestaurnatInfoFragment()
                    fragment.arguments=Bundle().apply {
                        putInt("storeidx",storeIdx) }
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