package com.example.nasa_funsies.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.nasa_funsies.databinding.FragmentAsteroidDetailBinding

class AsteroidDetailFragment : Fragment() {
    private val args: AsteroidDetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentAsteroidDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAsteroidDetailBinding.inflate(inflater)
        binding.asteroid = args.selectedAsteroid

        return binding.root
    }
}