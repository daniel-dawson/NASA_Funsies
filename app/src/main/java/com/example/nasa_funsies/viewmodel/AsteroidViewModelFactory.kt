package com.example.nasa_funsies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nasa_funsies.repository.AsteroidRepository

class AsteroidViewModelFactory(private val asteroidRepository: AsteroidRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AsteroidViewModel::class.java)) {
            return AsteroidViewModel(asteroidRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}