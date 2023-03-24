package com.example.pokemonapp.repository

import com.example.pokemonapp.common.handleRequestFlow
import com.example.pokemonapp.services.ApiImpl
import javax.inject.Inject

class PokemonRepository @Inject constructor(private val pokemonApiImple: ApiImpl) {

    suspend fun getPokemon() =  handleRequestFlow { pokemonApiImple.getPokemon() }

}