package com.jhonr1.localization.helpers

import android.content.Context
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.RecyclerView
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import com.jhonr1.localization.interfaces.IRecyclerViewItem

class RecyclerItemOnClickListener(context: Context, private val recyclerView: RecyclerView,
                                  private val listener: IRecyclerViewItem
): RecyclerView.SimpleOnItemTouchListener() {

    private val gestureDetector = GestureDetectorCompat(context, object : GestureDetector.SimpleOnGestureListener(){
        override fun onSingleTapUp(e: MotionEvent): Boolean {
            val childView: View? = recyclerView.findChildViewUnder(e.x, e.y)
            if (childView != null)
                listener.onItemClick(childView, recyclerView.getChildAdapterPosition(childView))
            return true
        }
    })

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        return gestureDetector.onTouchEvent(e)
    }
}