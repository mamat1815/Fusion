package com.theartemis.fusion.utility

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.theartemis.fusion.R

object Utility {

    fun ImageView.loadImage(path: String?) {
        Glide.with(context)
            .load(path)
            .apply(RequestOptions.placeholderOf(R.drawable.placeholder_img))
            .error(R.drawable.placeholder_img)
            .into(this)
    }

}