package com.example.yogiyo_clone.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HorizontalRecyclerDecoration(divWidth:Int): RecyclerView.ItemDecoration() {
    val divWidth=divWidth
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        if(parent.getChildAdapterPosition(view)!= parent.adapter!!.itemCount-1) outRect.right=divWidth
    }


}
class VerticalRecyclerDecoration(divWidth:Int): RecyclerView.ItemDecoration() {
    val divWidth=divWidth
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        if(parent.getChildAdapterPosition(view)!= parent.adapter!!.itemCount-1) outRect.right=divWidth
    }


}