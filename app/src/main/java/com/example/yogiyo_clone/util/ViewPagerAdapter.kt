//package com.example.yogiyo_clone.util
//
//import android.R
//import android.annotation.SuppressLint
//import android.content.Context
//import android.content.Intent
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.LinearLayout
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import androidx.viewpager.widget.PagerAdapter
//import com.bumptech.glide.Glide
//import com.example.yogiyo_clone.src.main.category.models.Store
//import com.example.yogiyo_clone.src.order.OrderActivity
//import java.util.*
//
//
//internal class ViewPagerAdapter(var context: Context, var images: List<String>) : RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {
//    inner class ViewHolder(itemview: View):RecyclerView.ViewHolder(itemview){
//
//        fun bind(item: String){
//            Glide.with(itemView).load(item).into(itemView)
//        }
//
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.id, parent, false)
//        return ViewHolder(inflater)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bind(images[position])
//    }
//
//    override fun getItemCount(): Int {
//        return images.size
//    }
//
//}