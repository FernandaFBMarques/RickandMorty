package com.example.rickandmortyapp.ui.character.list

import androidx.recyclerview.widget.DiffUtil
import com.example.rickandmortyapp.ui.character.dto.Character

class CharacterDiffCallback : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }
}