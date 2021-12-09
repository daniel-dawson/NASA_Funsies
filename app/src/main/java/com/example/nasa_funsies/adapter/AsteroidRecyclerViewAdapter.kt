package com.example.nasa_funsies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nasa_funsies.databinding.FragmentAsteroidItemBinding
import com.example.nasa_funsies.model.Asteroid

class AsteroidRecyclerViewAdapter(val clickListener: AsteroidClickListener) :
    ListAdapter<Asteroid, AsteroidRecyclerViewAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val asteroid = getItem(position)
        holder.bind(asteroid, clickListener)
    }

    class ViewHolder(val binding: FragmentAsteroidItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(asteroid: Asteroid, clickListener: AsteroidClickListener) {
            binding.asteroid = asteroid
            binding.clickListener = clickListener
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                return ViewHolder(
                    FragmentAsteroidItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    class AsteroidClickListener(val callback: (asteroid: Asteroid) -> Unit) {
        fun onClick(asteroid: Asteroid) = callback(asteroid)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Asteroid>() {
        override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem == newItem
        }

    }
}