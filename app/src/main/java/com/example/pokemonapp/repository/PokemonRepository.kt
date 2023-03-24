package com.example.pokemonapp.repository

import com.example.pokemonapp.common.handleRequestFlow
import com.example.pokemonapp.model.Pokemon
import com.example.pokemonapp.services.ApiImpl
import retrofit2.http.Path
import javax.inject.Inject

class PokemonRepository @Inject constructor(private val pokemonApiImple: ApiImpl) {

    suspend fun getPokemon() =  handleRequestFlow { pokemonApiImple.getPokemon() }

    suspend fun getPokemonById(id: Int) =  handleRequestFlow { pokemonApiImple.getPokemonById(id) }

}