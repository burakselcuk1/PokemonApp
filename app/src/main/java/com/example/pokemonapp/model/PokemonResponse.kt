package com.example.pokemonapp.model

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val sprites: Sprites
)

data class Sprites(
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("front_shiny") val frontShiny: String?,
    @SerializedName("front_female") val frontFemale: String?,
    @SerializedName("front_shiny_female") val frontShinyFemale: String?,
    @SerializedName("back_default") val backDefault: String?,
    @SerializedName("back_shiny") val backShiny: String?,
    @SerializedName("back_female") val backFemale: String?,
    @SerializedName("back_shiny_female") val backShinyFemale: String?
)