package com.example.yogiyo_clone.src.main.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.yogiyo_clone.R
import com.example.yogiyo_clone.config.BaseFragment
import com.example.yogiyo_clone.databinding.FragmentSearchBinding
import com.example.yogiyo_clone.src.main.search.models.menusearch.MenuSearchResponse
import com.example.yogiyo_clone.src.main.search.models.storesearch.Store
import com.example.yogiyo_clone.src.main.search.models.storesearch.StoreSearchResponse


class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::bind,R.layout.fragment_search),SearchFragmentView {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchSearchbarEdittext.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                MainSearchService(this@SearchFragment).tryGetSearchMenuList(keyword = binding.searchSearchbarEdittext.text.toString(),
                    searchId = 0, limit = 0
                )
                MainSearchService(this@SearchFragment).tryGetSearchStoreList(keyword = binding.searchSearchbarEdittext.text.toString(),
                    sortId = 0, limit = 0, express = 0, takeout = 0
                )
            }

        })

    }

    override fun onGetStoreSearchSuccess(listResponse: StoreSearchResponse) {
        Log.d("store List", listResponse.result.stores.toString())
        val result = listResponse.result
        if (listResponse.result.countStore>0){
            binding.searchResultArea.visibility=View.VISIBLE
            binding.storeSearchResultArea.visibility=View.VISIBLE


            val adapter = StoreListViewAdapter(result.stores.toMutableList())
            adapter.notifyDataSetChanged()
            binding.storeSearchResultListview.adapter = adapter

        }
        binding.storeSearchResultCountTextview.text=result.countStore.toString()

    }

    override fun onGetStoreSearchFailure(message: String) {
        Log.d("store List", "fail")
    }

    override fun onGetMenuSearchSuccess(listResponse: MenuSearchResponse) {
        Log.d("menu List", listResponse.result.menus.toString())
        if (listResponse.result.countMenu>0){
            binding.searchResultArea.visibility=View.VISIBLE
            binding.menuSearchResultArea.visibility=View.VISIBLE


        }


    }

    override fun onGetMenuSearchFailure(message: String) {
        Log.d("menu List", "fail")
    }

    inner class StoreListViewAdapter(private val items: MutableList<Store>) : BaseAdapter() {
        override fun getCount(): Int = items.size
        override fun getItem(position: Int): Store = items[position]
        override fun getItemId(position: Int): Long = position.toLong()

        @SuppressLint("SetTextI18n", "ResourceAsColor", "ViewHolder", "ResourceType")
        override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
            this.notifyDataSetChanged()
            var convertView = view
            convertView = LayoutInflater.from(parent?.context).inflate(R.layout.row_restaurant_item, parent, false)
            val item: Store = items[position]

            if (convertView != null) {
                Glide.with(this@SearchFragment).load(item.icon).into(convertView.findViewById<ImageView>(R.id.restaurant_imageview))
                convertView.findViewById<TextView>(R.id.restaurant_name_textview).text = item.title
                convertView.findViewById<TextView>(R.id.review_grade_textview).text = item.totalRate?.toString()
                convertView.findViewById<TextView>(R.id.review_number_textview).text = "리뷰 ${item.countReview}"
                convertView.findViewById<TextView>(R.id.represent_discount_textview).visibility = View.GONE
                convertView.findViewById<TextView>(R.id.deliver_fee).text = item.menus
                convertView.findViewById<ImageView>(R.id.save_image_imageview).visibility = View.VISIBLE
                Glide.with(this@SearchFragment).load(R.drawable.ic_jjimed).into(convertView.findViewById<ImageView>(R.id.save_image_imageview))
            }

            return convertView
        }
    }


}