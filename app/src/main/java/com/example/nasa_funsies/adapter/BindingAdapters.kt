package com.example.nasa_funsies.adapter

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nasa_funsies.model.Asteroid
import com.example.nasa_funsies.model.ImageDuJour
import com.example.nasa_funsies.viewmodel.AsteroidStatus
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.squareup.picasso.Picasso

@BindingAdapter("listData")
fun RecyclerView.submitListData(data: List<Asteroid>) {
    val adapter = this.adapter as AsteroidRecyclerViewAdapter
    adapter.submitList(data)
}

@BindingAdapter("showProgress")
fun CircularProgressIndicator.determineVisibility(isLoading: AsteroidStatus) {
    this.visibility = if (isLoading == AsteroidStatus.LOADING) View.VISIBLE else View.GONE
}

@BindingAdapter("nasaImageSrc")
fun ImageView.loadNasaImage(imageDuJour: ImageDuJour?) {
    val uri = if (imageDuJour?.mediaType == "image") {
        imageDuJour.url
    } else {
        "https://apod.nasa.gov/apod/image/2001/STSCI-H-p2006a-h-1024x614.jpg"
    }
    Picasso
        .get()
        .also { it.setIndicatorsEnabled(true) }
        .load(uri)
        .into(this)
}
