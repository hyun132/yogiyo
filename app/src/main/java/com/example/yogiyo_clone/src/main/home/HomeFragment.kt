package com.example.yogiyo_clone.src.main.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.yogiyo_clone.R
import com.example.yogiyo_clone.config.ApplicationClass.Companion.addComma
import com.example.yogiyo_clone.config.BaseFragment
import com.example.yogiyo_clone.databinding.FragmentHomeBinding
import com.example.yogiyo_clone.databinding.FragmentViewpagerBinding
import com.example.yogiyo_clone.src.login.LoginActivity
import com.example.yogiyo_clone.src.main.home.models.Category
import com.example.yogiyo_clone.src.main.home.models.FirstAd
import com.example.yogiyo_clone.src.main.home.models.HomeResponse
import com.example.yogiyo_clone.src.main.home.models.bottom.BottomRecommandResponse
import com.example.yogiyo_clone.src.main.home.models.bottom.Result
import com.example.yogiyo_clone.src.order.OrderActivity
import com.example.yogiyo_clone.src.searchaddress.SearchAddressMainActivity
import com.example.yogiyo_clone.util.HorizentalFragment
import com.example.yogiyo_clone.util.HorizontalRecyclerDecoration

class HomeFragment :
    BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home),
    HomeFragmentView {

    private lateinit var viewPager: ViewPager2

    val categories = listOf<Category>(
        Category("전체보기", R.drawable.circle_background, 0),
        Category("중국집", R.drawable.circle_background, 2),
        Category("치킨", R.drawable.circle_background, 3),
        Category("한식", R.drawable.circle_background, 4),
        Category("피자/양식", R.drawable.circle_background, 5),
        Category("찜/탕", R.drawable.circle_background, 6),
        Category("카페/디저트", R.drawable.circle_background, 7),
        Category("분식", R.drawable.circle_background, 8),
        Category("1인분주문", R.drawable.circle_background, 9),
        Category("일식/돈까스", R.drawable.circle_background, 10),
        Category("야식", R.drawable.circle_background, 11),
        Category("족발/보쌈", R.drawable.circle_background, 12),
        Category("프랜차이즈", R.drawable.circle_background, 13),
        Category("편의점/마트", R.drawable.circle_background, 14),
        Category("테이크아웃", R.drawable.circle_background, 15),
//                Category("익스프레스",R.drawable.circle_background,1),
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var cateogoryAdapter = HomeCategoryAdapter()
        var recyclerview = binding.categoryRecyclerview
        cateogoryAdapter.differ.submitList(categories)
        recyclerview.adapter = cateogoryAdapter

//        recyclerview.onInterceptTouchEvent(e)

        cateogoryAdapter.setItemClickListener(object : HomeCategoryAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
                Log.d("home : ", "item clicked")
            }
        })

        binding.categoryRecyclerview.layoutManager =
            GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false)
        binding.categoryRecyclerview.addItemDecoration(HorizontalRecyclerDecoration(32))
        binding.addressButton.setOnClickListener {
            var intent = Intent(context, SearchAddressMainActivity::class.java)
            startActivity(intent)
        }


//        HomeService(this).tryGetHomeInfo()

    }

    override fun onResume() {
        super.onResume()
        HomeService(this).tryGetHomeInfo()
        HomeService(this).tryGetHomeBottomRecommand()
    }

    override fun onGetHomeInfoSuccess(response: HomeResponse) {

        val viewPagerAdapter =
            ViewPagerAdapter(this, response.result.firstAd)   //이 프래그먼트에 뷰페이저 어답터 생성.
        viewPager = binding.adViewpager
        viewPager.adapter = viewPagerAdapter  //viewPager에 Adapter 연결해줌.

        binding.addressButton.text = "${response.result.address} ▾"
        val newFragment = HorizentalFragment()
        newFragment.arguments = Bundle().apply {
            putSerializable("theme", response.result.themes[0])
        }
//        newFragment.data=
        val transaction = requireActivity().supportFragmentManager.beginTransaction().apply {
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            replace(R.id.theme1_fragment, newFragment)
            addToBackStack(null)
        }
        // Commit the transaction
        transaction.commit();
    }

    override fun onGetHomeInfoFailure(message: String) {
        val intent = Intent(activity, LoginActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    override fun onGetBottomRecommadSuccess(response: BottomRecommandResponse) {

        val adapter = response.result?.let { ListViewAdapter(it) }
        binding.recommandBottomListview.adapter=adapter

        binding.recommandBottomListview.setOnItemClickListener(object : AdapterView.OnItemClickListener{
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                    val intent = Intent(requireContext(), OrderActivity::class.java)
                    intent.putExtra("storeId", response.result?.get(position)?.storeIdx)
                    requireActivity().startActivity(intent)
            }

        })

    }

    override fun onGetBottomRecommadFailure(message: String) {
        Log.d("getBottom : ", "fail")
    }

    class ViewPagerAdapter(fragment: Fragment, data: List<FirstAd>) :
        FragmentStateAdapter(fragment) {

        var data = data
        override fun getItemCount(): Int = data.size
        override fun createFragment(position: Int): Fragment {
            val fragment = ViewpagerFragment()
            fragment.position = position
            fragment.imageUri = data
            return fragment
        }
    }

    class ViewpagerFragment : BaseFragment<FragmentViewpagerBinding>(
        FragmentViewpagerBinding::bind,
        R.layout.fragment_viewpager
    ) {
        var position: Int = 0
        lateinit var imageUri: List<FirstAd>
        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            Glide.with(this).load(imageUri[position].src).centerCrop().into(binding.slidingImage)
        }

    }

    inner class ListViewAdapter(private val items: List<Result>) : BaseAdapter() {
        override fun getCount(): Int = items.size
        override fun getItem(position: Int): Result = items[position]
        override fun getItemId(position: Int): Long = position.toLong()

        @SuppressLint("SetTextI18n", "ResourceAsColor", "ViewHolder")
        override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
            this.notifyDataSetChanged()
            var convertView = view
            convertView = LayoutInflater.from(parent?.context).inflate(R.layout.bottom_recommand_item, parent, false)
            val item: Result = items[position]

            if (convertView != null) {

                var src = item.poster?.split(',')
                if (src != null) {
                    if (src.size<3){
                        convertView.findViewById<ImageView>(R.id.bottom_image_3).visibility=View.GONE
                        convertView.findViewById<ImageView>(R.id.bottom_image_2).visibility=View.GONE
                        Glide.with(this@HomeFragment).load(src[0]).into(convertView.findViewById<ImageView>(R.id.bottom_image_1))
                    }else{
                        Glide.with(this@HomeFragment).load(src[0]).into(convertView.findViewById<ImageView>(R.id.bottom_image_1))
                        Glide.with(this@HomeFragment).load(src[1]).into(convertView.findViewById<ImageView>(R.id.bottom_image_2))
                        Glide.with(this@HomeFragment).load(src[2]).into(convertView.findViewById<ImageView>(R.id.bottom_image_3))
                    }
                }

                convertView.findViewById<TextView>(R.id.restaurant_name_textview).text = item.title
                convertView.findViewById<TextView>(R.id.delivery_time_textview).text = item.deliveryTime.toString()
                convertView.findViewById<TextView>(R.id.reivew_score_textview).text = item.rateAvg.toString()
                convertView.findViewById<TextView>(R.id.review_number_textview).text = "리뷰 ${item.countReview}"
                if (item.deliveryCharge!! >0) convertView.findViewById<TextView>(R.id.delivery_fee_textview).text = "배달요금 ${addComma(item.deliveryCharge)}원"

            }



            return convertView
        }
    }

}

