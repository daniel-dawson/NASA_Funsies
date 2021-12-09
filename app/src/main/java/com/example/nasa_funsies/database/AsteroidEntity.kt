package com.example.nasa_funsies.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.nasa_funsies.model.Asteroid

@Entity
data class DatabaseAsteroid(
    @PrimaryKey
    val id: Long,
    val name: String,
    val absoluteMagnitude: Double,
    val estimatedDiameterKm: Double,
    val isPotentiallyHazardous: Boolean,
    val relativeVelocityKmPerS: Double,
    val missDistanceAu: Double,
    val closeApproachDate: String
)

fun List<DatabaseAsteroid>.asDomainModel(): List<Asteroid> {
    return map {
        Asteroid(
            id = it.id,
            name = it.name,
            absoluteMagnitude = it.absoluteMagnitude,
            estimatedDiameterKm = it.estimatedDiameterKm,
            isPotentiallyHazardous = it.isPotentiallyHazardous,
            relativeVelocityKmPerS = it.relativeVelocityKmPerS,
            missDistanceAu = it.missDistanceAu,
            closeApproachDate = it.closeApproachDate,
        )
    }
}

fun List<Asteroid>.asDatabaseModel(): Array<DatabaseAsteroid> {
    return map {
        DatabaseAsteroid(
            id = it.id,
            name = it.name,
            absoluteMagnitude = it.absoluteMagnitude,
            estimatedDiameterKm = it.estimatedDiameterKm,
            isPotentiallyHazardous = it.isPotentiallyHazardous,
            relativeVelocityKmPerS = it.relativeVelocityKmPerS,
            missDistanceAu = it.missDistanceAu,
            closeApproachDate = it.closeApproachDate,
        )
    }.toTypedArray()
}