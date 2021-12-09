package com.example.nasa_funsies.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.nasa_funsies.adapter.AsteroidRecyclerViewAdapter
import com.example.nasa_funsies.database.getDatabase
import com.example.nasa_funsies.databinding.FragmentAsteroidListBinding
import com.example.nasa_funsies.repository.AsteroidRepository
import com.example.nasa_funsies.viewmodel.AsteroidViewModel
import com.example.nasa_funsies.viewmodel.AsteroidViewModelFactory

/**
 * A fragment representing a list of Items.
 */
class AsteroidFragment : Fragment() {

    private val database by lazy {
        getDatabase(requireActivity().applicationContext)
    }
    private val asteroidRepository by lazy {
        AsteroidRepository(database)
    }
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
        binding.asteroidRecyclerList.adapter = AsteroidRecyclerViewAdapter(
            AsteroidRecyclerViewAdapter.AsteroidClickListener {
                viewModel.displayAsteroidDetails(it)
            })
        binding.viewModel = viewModel

        viewModel.navigateToAsteroidDetails.observe(viewLifecycleOwner, {
            if (it != null) {
                this.findNavController().navigate(
                    AsteroidFragmentDirections.actionAsteroidFragmentToAsteroidDetailFragment(it)
                )
                viewModel.onDisplayAsteroidDetailsHandler()
            }
        })

        return binding.root
    }
}