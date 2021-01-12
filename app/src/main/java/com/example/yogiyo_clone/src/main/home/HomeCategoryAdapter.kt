package com.example.yogiyo_clone.src.main.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yogiyo_clone.R
import com.example.yogiyo_clone.src.main.MainActivity
import com.example.yogiyo_clone.src.main.category.CategoryFragment
import com.example.yogiyo_clone.src.main.category.CategoryFragment2
import com.example.yogiyo_clone.src.main.home.models.Category

class HomeCategoryAdapter:RecyclerView.Adapter<HomeCategoryAdapter.CategoryViewHolder>() {


    inner class CategoryViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(item:Category){
            itemView.findViewById<TextView>(R.id.category_title).text=item.category_name
            Glide.with(itemView.context).load(item.category_img).into(itemView.findViewById(R.id.category_imageview))

            itemView.setOnClickListener {
                val newFragment = CategoryFragment()
                val transaction = (itemView.context as MainActivity).supportFragmentManager.beginTransaction().apply {
                    // Replace whatever is in the fragment_container view with this fragment,
                    // and add the transaction to the back stack so the user can navigate back
                    addToBackStack(null)
                    newFragment.arguments=Bundle().apply {  putInt("categoryId", item.dategory_id)}

                    replace(R.id.main_frm, newFragment)
                }
                transaction.commit();
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        var inflater =LayoutInflater.from(parent.context).inflate(R.layout.category_item,parent,false)
        return CategoryViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    //클릭 인터페이스 정의
    interface ItemClickListener {
        fun onClick(view: View, position: Int)
    }

    //클릭리스너 선언
    private lateinit var itemClickListner: ItemClickListener

    //클릭리스너 등록 매소드
    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListner = itemClickListener
    }

    private val differCallback = object : DiffUtil.ItemCallback<Category>() {
        //둘이 같은 객체인지
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.dategory_id == newItem.dategory_id
        }

        //둘이 같은 아이템인지
        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)
}