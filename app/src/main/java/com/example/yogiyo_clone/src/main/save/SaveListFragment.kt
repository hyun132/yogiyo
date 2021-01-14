package com.example.yogiyo_clone.src.main.save

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.yogiyo_clone.R
import com.example.yogiyo_clone.config.BaseFragment
import com.example.yogiyo_clone.databinding.FragmentSaveListBinding
import com.example.yogiyo_clone.src.main.save.models.SaveListResponse
import com.example.yogiyo_clone.src.main.save.models.Store
import com.example.yogiyo_clone.src.order.orderfood.OrderFragment
import com.example.yogiyo_clone.src.order.orderfood.OrderService
import com.example.yogiyo_clone.util.VerticalAdapter

class SaveListFragment : BaseFragment<FragmentSaveListBinding>(
    FragmentSaveListBinding::bind,
    R.layout.fragment_save_list
),
    SaveListFragmentView {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        SaveListService(this).tryGetSaveList()
    }

    override fun onGetSaveListSuccess(listResponse: SaveListResponse) {

        val result = listResponse.result
        val adapter = ListViewAdapter(result.stores.toMutableList())
        adapter.notifyDataSetChanged()
        binding.savelistListview.adapter = adapter
    }

    override fun onGetSaveListFailure(message: String) {

    }

    inner class ListViewAdapter(private val items: MutableList<Store>) : BaseAdapter() {
        override fun getCount(): Int = items.size
        override fun getItem(position: Int): Store = items[position]
        override fun getItemId(position: Int): Long = position.toLong()

        @SuppressLint("SetTextI18n", "ResourceAsColor", "ViewHolder")
        override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
            this.notifyDataSetChanged()
            var convertView = view
            convertView = LayoutInflater.from(parent?.context).inflate(R.layout.row_restaurant_item, parent, false)
            val item: Store = items[position]

            if (convertView != null) {
                Glide.with(this@SaveListFragment).load(item.icon).into(convertView.findViewById<ImageView>(R.id.restaurant_imageview))
                convertView.findViewById<TextView>(R.id.restaurant_name_textview).text = item.title
                convertView.findViewById<TextView>(R.id.review_grade_textview).text = item.rateAvg.toString()
                convertView.findViewById<TextView>(R.id.review_number_textview).text = item.countReview.toString()
                convertView.findViewById<TextView>(R.id.represent_discount_textview).visibility = View.GONE
                convertView.findViewById<TextView>(R.id.deliver_fee).text = item.menus
                convertView.findViewById<ImageView>(R.id.save_image_imageview).visibility = View.VISIBLE
                Glide.with(this@SaveListFragment).load(item.icon).into(convertView.findViewById<ImageView>(R.id.save_image_imageview))

            }

            return convertView
        }
    }

}