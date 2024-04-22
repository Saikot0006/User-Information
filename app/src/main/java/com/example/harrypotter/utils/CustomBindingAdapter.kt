package com.example.harrypotter.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.harrypotter.R

@BindingAdapter("setImage")
fun setImage(imageView: ImageView,url:String?){
    try {
        Glide.with(imageView)
            .load(url)
            .placeholder(R.mipmap.ic_launcher)
            .into(imageView)
    }catch (_:Exception){}
}