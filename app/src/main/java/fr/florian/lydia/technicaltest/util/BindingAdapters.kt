package fr.florian.lydia.technicaltest.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


object DataBindingAdapters {
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun getLargeImage(view: ImageView, url: String?) = run {
        Glide.with(view.context).load(url).into(view)
    }
}