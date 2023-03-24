package com.example.pokemonapp.services

import javax.inject.Inject

class ApiImpl @Inject constructor(private val api: Api) {

    suspend fun getPokemon() = api.getPokemon()

}