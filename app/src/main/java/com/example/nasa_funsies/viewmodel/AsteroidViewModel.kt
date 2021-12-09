package com.example.nasa_funsies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.nasa_funsies.model.Asteroid
import com.example.nasa_funsies.model.ImageDuJour
import com.example.nasa_funsies.repository.AsteroidRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import timber.log.Timber

enum class AsteroidStatus { LOADING, ERROR, DONE }

class AsteroidViewModel(
    private val asteroidRepository: AsteroidRepository
) : ViewModel() {

    private val _navigateToAsteroidDetails = MutableLiveData<Asteroid?>()
    val navigateToAsteroidDetails: LiveData<Asteroid?>
        get() = _navigateToAsteroidDetails

    fun displayAsteroidDetails(asteroid: Asteroid) {
        _navigateToAsteroidDetails.value = asteroid
    }

    fun onDisplayAsteroidDetailsHandler() {
        _navigateToAsteroidDetails.value = null
    }

    private val _status = MutableLiveData<AsteroidStatus>()
    val status: LiveData<AsteroidStatus>
        get() = _status

    val asteroids: LiveData<List<Asteroid>> = liveData {
        emit(listOf())
        asteroidRepository.getAsteroidsForWeek()
            .onStart {
                Timber.d("Status -> Loading")
                _status.value = AsteroidStatus.LOADING
            }
//            .catch {
//                Timber.d("Status -> Error")
//                _status.value = AsteroidStatus.ERROR
//            }
            .collect {
                Timber.d("Status -> Done")
                _status.value = AsteroidStatus.DONE
                emit(it)
            }
    }

    val imageDuJour: LiveData<ImageDuJour> = liveData {
        asteroidRepository.getImageDuJour()
            .collect {
                Timber.d("Image -> $it")
                emit(it)
            }
    }
}