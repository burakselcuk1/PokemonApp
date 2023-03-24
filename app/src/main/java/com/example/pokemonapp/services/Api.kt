package com.example.pokemonapp.services

import com.example.pokemonapp.model.Pokemon
import com.example.pokemonapp.model.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("pokemon")
    suspend fun getPokemon(): Response<Pokemon>

    @GET("pokemon/{id}")
    suspend fun getPokemonById(@Path("id") id: Int): Response<PokemonResponse>

}