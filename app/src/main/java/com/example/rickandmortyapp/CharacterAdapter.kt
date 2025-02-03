package com.example.rickandmortyapp
import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import com.example.rickandmortyapp.databinding.CharactersBinding
import com.bumptech.glide.Glide

class CharacterAdapter : ListAdapter<Character, CharacterAdapter.CharacterHolder>(CharacterDiffCallback()) {

    inner class CharacterHolder(val binding: CharactersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character) {
            binding.name.text = character.name
            binding.status.text = character.status
            binding.species.text = character.species
            binding.type.text = character.type
            binding.gender.text = character.gender
            binding.origin.text = character.origin.name
            binding.location.text = character.location.name
            binding.episode.text = character.episode.toString()
            binding.url.text = character.url
            binding.created.text = character.created

            Glide.with(binding.root.context)
                .load(character.url).
                into(binding.imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterHolder {
        val binding = CharactersBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CharacterHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)
    }

}
