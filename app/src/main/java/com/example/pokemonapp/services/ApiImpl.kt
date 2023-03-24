package com.example.pokemonapp.services

import com.example.pokemonapp.model.Pokemon
import com.example.pokemonapp.model.PokemonResponse
import retrofit2.Response
import retrofit2.http.Path
import javax.inject.Inject

class ApiImpl @Inject constructor(private val api: Api) {

    suspend fun getPokemon() = api.getPokemon()

    suspend fun getPokemonById(id: Int)= api.getPokemonById(id)

}