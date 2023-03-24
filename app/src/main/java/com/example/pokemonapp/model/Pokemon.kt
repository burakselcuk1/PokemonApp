package com.example.pokemonapp.model

data class Pokemon(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)