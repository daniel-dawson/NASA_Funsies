package com.example.nasa_funsies.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.nasa_funsies.adapter.AsteroidRecyclerViewAdapter
import com.example.nasa_funsies.databinding.FragmentAsteroidListBinding
import com.example.nasa_funsies.repository.AsteroidRepository
import com.example.nasa_funsies.viewmodel.AsteroidViewModel
import com.example.nasa_funsies.viewmodel.AsteroidViewModelFactory

/**
 * A fragment representing a list of Items.
 */
class AsteroidFragment : Fragment() {

    private val asteroidRepository by lazy { AsteroidRepository() }
    private val viewModel: AsteroidViewModel by activityViewModels {
        AsteroidViewModelFactory(asteroidRepository)
    }

    private lateinit var binding: FragmentAsteroidListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAsteroidListBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.asteroidRecyclerList.adapter = AsteroidRecyclerViewAdapter()
        binding.viewModel = viewModel

        return binding.root
    }
}