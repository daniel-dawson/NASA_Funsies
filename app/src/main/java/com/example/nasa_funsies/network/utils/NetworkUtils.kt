package com.example.nasa_funsies.network.utils

import com.example.nasa_funsies.model.Asteroid
import com.example.nasa_funsies.network.API_DATE_FORMAT
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

fun parseAsteroidsJson(response: JSONObject): List<Asteroid> {
    val root = response.getJSONObject("near_earth_objects")
    val asteroids = mutableListOf<Asteroid>()

    val calendar = Calendar.getInstance()
    val dateFormatter = SimpleDateFormat(API_DATE_FORMAT, Locale.getDefault())

    for (i in 0..6) {
        val time = calendar.time
        val formattedTime = dateFormatter.format(time)
        val asteroidArray = root.getJSONArray(formattedTime)

        for (j in 0 until asteroidArray.length()) {
            val asteroidJson = asteroidArray.getJSONObject(j)
            val id = asteroidJson.getLong("id")
            val name = asteroidJson.getString("name")
            val absMagnitude = asteroidJson.getDouble("absolute_magnitude_h")
            val diameter = asteroidJson.getJSONObject("estimated_diameter")
                .getJSONObject("kilometers")
                .getDouble("estimated_diameter_max")
            val isHazardous = asteroidJson.getBoolean("is_potentially_hazardous_asteroid")
            val closeApproachData = asteroidJson.getJSONArray("close_approach_data")
            val data = closeApproachData.getJSONObject(0)
            val relativeVelocity = data.getJSONObject("relative_velocity")
                .getDouble("kilometers_per_second")
            val missDistance = data.getJSONObject("miss_distance")
                .getDouble("astronomical")

            val newAsteroid = Asteroid(
                id = id,
                name = name,
                absoluteMagnitude = absMagnitude,
                estimatedDiameterKm = diameter,
                isPotentiallyHazardous = isHazardous,
                closeApproachDate = formattedTime,
                relativeVelocityKmPerS = relativeVelocity,
                missDistanceAu = missDistance
            )
            asteroids.add(newAsteroid)
        }

        calendar.add(Calendar.DAY_OF_YEAR, 1)
    }

    return asteroids
}