package com.example.yogiyo_clone.src.main.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.yogiyo_clone.R
import com.example.yogiyo_clone.config.BaseFragment
import com.example.yogiyo_clone.databinding.FragmentHomeBinding
import com.example.yogiyo_clone.src.main.home.models.Category
import com.example.yogiyo_clone.src.main.home.models.HomeResponse
import com.example.yogiyo_clone.src.searchaddress.SearchAddressMainActivity
import com.example.yogiyo_clone.util.HorizentalFragment
import com.example.yogiyo_clone.util.HorizontalRecyclerDecoration

class HomeFragment :
    BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home),
    HomeFragmentView {

        val categories = listOf<Category>(
        Category("전체보기",R.drawable.circle_background,0),
        Category("중국집",R.drawable.circle_background,2),
        Category("한식",R.drawable.circle_background,4),
        Category("찜/탕",R.drawable.circle_background,6),
        Category("분식",R.drawable.circle_background,8),
        Category("일식/돈까스",R.drawable.circle_background,10),
        Category("족발/보쌈",R.drawable.circle_background,12),
        Category("편의점/마트",R.drawable.circle_background,14),
        Category("테이크아웃",R.drawable.circle_background,15),
        Category("치킨",R.drawable.circle_background,3),
        Category("피자/양식",R.drawable.circle_background,5),
        Category("카페/디저트",R.drawable.circle_background,7),
        Category("1인분주문",R.drawable.circle_background,9),
        Category("야식",R.drawable.circle_background,11),
        Category("프랜차이즈",R.drawable.circle_background,13),
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
                Log.d("home : ","item clicked")
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
    }

    override fun onGetHomeInfoSuccess(response: HomeResponse) {

        binding.addressButton.text = "${response.result.address} ▾"
        val newFragment = HorizentalFragment()
        newFragment.arguments=Bundle().apply {
            putSerializable("theme",response.result.themes[0])
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
//        val intent = Intent(activity,LoginActivity::class.java)
//        startActivity(intent)
//        activity?.finish()
    }



}

