package com.example.nasa_funsies.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nasa_funsies.R
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
        .placeholder(R.drawable.placeholder_picture_of_day)
        .into(this)
}

@BindingAdapter("asteroidStatusImage")
fun ImageView.setStatusImage(isPotentiallyHazardous: Boolean) {
    setImageResource(
        if (isPotentiallyHazardous) {
            R.drawable.asteroid_hazardous
        } else {
            R.drawable.asteroid_safe
        }
    )
}

@BindingAdapter("asteroidStatusIcon")
fun ImageView.setStatusIcon(isPotentiallyHazardous: Boolean) {
    setImageResource(
        if (isPotentiallyHazardous) {
            R.drawable.ic_status_potentially_hazardous
        } else {
            R.drawable.ic_status_normal
        }
    )
}

@BindingAdapter("astronomicalUnitText")
fun TextView.setAstronomicalUnitText(astronomicalUnits: Double) {
    text = astronomicalUnits.toString()
}

@BindingAdapter("kmUnitText")
fun TextView.setKmUnitText(kmUnits: Double) {
    text = kmUnits.toString()
}

@BindingAdapter("velocityText")
fun TextView.setvelocityText(kmUnits: Double) {
    text = kmUnits.toString()
}
