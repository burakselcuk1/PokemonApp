package com.example.pokemonapp.services

import com.example.pokemonapp.model.Pokemon
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("pokemon")
    suspend fun getPokemon(): Response<Pokemon>

}