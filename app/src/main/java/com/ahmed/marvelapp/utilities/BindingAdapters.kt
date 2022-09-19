package com.ahmed.marvelapp.utilities

import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahmed.marvelapp.R
import com.ahmed.marvelapp.domain.sealeds.RequestState
import com.bumptech.glide.Glide

@BindingAdapter(value = ["app:imagePath", "app:imageExtension"], requireAll = true)
fun loadImage(imageView: ImageView, imagePath: String?, imageExtension: String?) {
    if (imagePath != null && imageExtension != null) {
        Glide.with(imageView).load("$imagePath.$imageExtension").into(imageView)
    } else {
        Glide.with(imageView).load(R.drawable.ic_launcher_background).into(imageView)
    }
}

@BindingAdapter(value = ["app:spacingSize", "app:spacingEdgeEnabled"], requireAll = true)
fun bindAdapter(view: RecyclerView, spacingSize: Float, spacingEdgeEnabled: Boolean) {
    with(view) {
        addItemDecoration(
            AdaptiveSpacingItemDecoration(spacingSize.toInt(), spacingEdgeEnabled)
        )
    }
}

@BindingAdapter(value = ["app:showWhenLoading"])
fun <T> showWhenLoading(view: View, state: RequestState<T>) {
    view.isVisible = state is RequestState.Loading
}

@BindingAdapter(value = ["app:showWhenSuccess"])
fun <T> showWhenSuccess(view: View, state: RequestState<T>) {
    view.isVisible = state is RequestState.Success
}

@BindingAdapter(value = ["app:showWhenError"])
fun <T> showWhenError(view: View, state: RequestState<T>) {
    view.isVisible = state is RequestState.Error
}