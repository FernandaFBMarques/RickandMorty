package com.example.rickandmortyapp.helper

inline fun <T> T.whenChanged(other: T?, block: (T) -> Unit) {
    if (this != other) block(this)
}