package com.example.nasa_funsies.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Asteroid(
    val id: Long,
    val name: String,
    val absoluteMagnitude: Double,
    val estimatedDiameterKm: Double,
    val isPotentiallyHazardous: Boolean,
    val relativeVelocityKmPerS: Double,
    val missDistanceAu: Double,
    val closeApproachDate: String
) : Parcelable