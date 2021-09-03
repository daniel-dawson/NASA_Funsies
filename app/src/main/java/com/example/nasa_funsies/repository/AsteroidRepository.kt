package com.example.nasa_funsies.repository

import com.example.nasa_funsies.BuildConfig
import com.example.nasa_funsies.model.Asteroid
import com.example.nasa_funsies.model.ImageDuJour
import com.example.nasa_funsies.network.API_DATE_FORMAT
import com.example.nasa_funsies.network.NasaApi
import com.example.nasa_funsies.network.utils.parseAsteroidsJson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.json.JSONObject
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

class AsteroidRepository {
    suspend fun getAsteroidsForWeek(): Flow<List<Asteroid>> =
        flow {
            val calendar = Calendar.getInstance()
            val dateFormatter = SimpleDateFormat(API_DATE_FORMAT, Locale.getDefault())
            val startTime = calendar.time
            val formattedStart = dateFormatter.format(startTime)
            val endTime = calendar.also { it.add(Calendar.DAY_OF_YEAR, 6) }.time
            val formattedEnd = dateFormatter.format(endTime)
            Timber.d("Firing service call -> $formattedStart, $formattedEnd")
            val response = NasaApi.retrofitService.getAsteroids(
                formattedStart,
                formattedEnd,
                BuildConfig.NASA_API_KEY
            )
            Timber.d("Response -> $response")
            if (response.isSuccessful) {
                val parsedResponse = parseAsteroidsJson(
                    JSONObject(response.body() ?: "")
                )
                emit(parsedResponse)
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getImageDuJour(): Flow<ImageDuJour> =
        flow {
            val response = NasaApi.retrofitService.getImageDuJour(
                BuildConfig.NASA_API_KEY
            )
            Timber.d("Image response -> $response")
            if (response.isSuccessful) {
                emit(response.body()!!)
            }
        }.flowOn(Dispatchers.IO)

}