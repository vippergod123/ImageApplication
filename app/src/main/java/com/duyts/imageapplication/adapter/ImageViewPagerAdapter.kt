package com.duyts.imageapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.duyts.imageapplication.R


class ImageViewPagerAdapter(private val context: Context, private val images: List<String>) :
    RecyclerView.Adapter<ImageAdapter.Companion.ImageViewHolder>() {

    companion object {
        class ImageViewHolder(v: View) : RecyclerView.ViewHolder(v) {
            val imageView: ImageView = v.findViewById(R.id.imageView)
            fun onBindViewHolder(context: Context, item: String) {
                Glide.with(context).load(item).into(imageView)
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImageAdapter.Companion.ImageViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.image_viewpager_viewholder, parent, false)
        return ImageAdapter.Companion.ImageViewHolder(view)
    }


    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: ImageAdapter.Companion.ImageViewHolder, position: Int) {
        val item = images[position]
        holder.onBindViewHolder(context, item)
    }

}