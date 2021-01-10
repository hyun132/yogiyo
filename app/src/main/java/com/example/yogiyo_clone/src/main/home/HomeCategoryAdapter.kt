package com.example.yogiyo_clone.src.main.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yogiyo_clone.R
import com.example.yogiyo_clone.src.main.home.models.Category

class HomeCategoryAdapter(categories:Array<Category>):RecyclerView.Adapter<HomeCategoryAdapter.CategoryViewHolder>() {

    val dataArray=categories

    inner class CategoryViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(item:Category){
            itemView.findViewById<TextView>(R.id.category_title).text=item.category_name
            Glide.with(itemView.context).load(item.category_img).into(itemView.findViewById(R.id.category_imageview))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        var inflater =LayoutInflater.from(parent.context).inflate(R.layout.category_item,parent,false)
        return CategoryViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(dataArray[position])
    }

    override fun getItemCount(): Int {
        return dataArray.size
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
}