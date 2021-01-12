package com.example.yogiyo_clone.src.main.category

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yogiyo_clone.R
import com.example.yogiyo_clone.config.BaseFragment
import com.example.yogiyo_clone.databinding.FragmentCategoryContainerBinding
import com.example.yogiyo_clone.src.main.category.models.CategoryRequest
import com.example.yogiyo_clone.src.main.category.models.CategoryResult
import com.example.yogiyo_clone.util.VerticalAdapter
import com.google.android.material.tabs.TabLayout


class CategoryFragment : BaseFragment<FragmentCategoryContainerBinding>(
    FragmentCategoryContainerBinding::bind,
    R.layout.fragment_category_container
) ,
CategoryFragmentView{

    val categories:HashMap<Int, String> = HashMap<Int, String>()

//    val categories = listOf(arrayOf(0,"전체보기"), arrayOf(2,"중국집"), arrayOf(3,"치킨"), arrayOf(4,"한식"), arrayOf(5,"피자/양식"))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categories.put(0, "전체보기")
        categories.put(2, "중국집")
        categories.put(3, "치킨")
        categories.put(4, "한식")
        categories.put(5, "피자/양식")
        categories.put(6, "찜/탕")
        categories.put(7, "카페/디저트")
        categories.put(8, "분식")
        categories.put(9, "1인분주문")
        categories.put(10, "일식/돈까스")
        categories.put(11, "야식")
        categories.put(12, "족발/보쌈")
        categories.put(13, "프랜차이즈")
        categories.put(14, "편의점/마트")

        val categoryIdx= arrayListOf<Int>(0,0,1,2,3,4,5,6,7,8,9,10,11,12)

        setUpTab()
        val category = arguments?.getInt("categoryId")


        if (category != null) {
            binding.homeTab.getTabAt(category.toInt())?.select()
//            changeCategory(category.toInt())
            val request = CategoryRequest(category,false,0,1)

            binding.homeTab.setScrollX(binding.homeTab.getWidth());
            binding.homeTab.getTabAt(categoryIdx[category])?.select()

            CategoryService(this).tryGetCategoryInfo(request)
        }

        binding.homeTab.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    Log.d("tab : ",tab.text.toString())
                }
                val request = getKey(categories, tab?.text).let { it?.let { it1 ->
                    CategoryRequest(
                        it1.toInt(),false,0,1)
                } }
                if (request != null) {
                    CategoryService(this@CategoryFragment).tryGetCategoryInfo(request)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

    }

    fun <K, V> getKey(map: Map<K, V>, target: V): K? {
        for ((key, value) in map) {
            if (target == value) {
                return key
            }
        }
        return null
    }

    override fun onCategoryInfoSuccess(result: CategoryResult) {
        val adapter = VerticalAdapter()
//        adapter.setItemClickListener(object :VerticalAdapter.ItemClickListener{
//            override fun onClick(view: View, position: Int) {
//                val intent = Intent(activity,OrderActivity::class.java)
//                intent.putExtra("storeId",adapter.differ.currentList[position].storeIdx)
//                startActivity(intent)
//            }
//
//        })
        adapter.differ.submitList(result.stores)
        binding.yogiyoRecyclerview.adapter=adapter
        binding.yogiyoRecyclerview.layoutManager=LinearLayoutManager(requireContext())
    }

    override fun onCategoryInfoFailure(message: String) {
        TODO("Not yet implemented")
    }

    fun changeCategory(idx: Int){
//        if (request != null) {
//            CategoryService(this@CategoryFragment).tryGetCategoryInfo(idx)
//        }
    }

    private fun setUpTab() {
//        viewPagerAdapter = ViewPagerAdapter(getSupportFragmentManager(), this)
        for (i in categories) {
            binding.homeTab.addTab(
                binding.homeTab.newTab()
                    .setText(i.value)
            )
        }
        binding.homeTab.setTabGravity(TabLayout.GRAVITY_FILL)
//        viewPager.setAdapter(viewPagerAdapter)
    }
}