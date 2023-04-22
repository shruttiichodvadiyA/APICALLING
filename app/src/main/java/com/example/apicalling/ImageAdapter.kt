package com.example.apicalling

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import java.util.Objects
import kotlin.collections.ArrayList

@Suppress("UNREACHABLE_CODE")
class ImageAdapter(var context: Context, var images: ArrayList<String>) : PagerAdapter() {
    override fun getCount(): Int {
        return images.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object` as LinearLayout
    }

    @SuppressLint("MissingInflatedId")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val itemView: View = LayoutInflater.from(container.context)
            .inflate(R.layout.imageslider_item_file, container, false)
        var imgproduct: ImageView = itemView.findViewById(R.id.imgproduct)
        Glide.with(context).load("${images[position]}").placeholder(R.drawable.placeholderimg).into(imgproduct)
        Objects.requireNonNull(container).addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }
}