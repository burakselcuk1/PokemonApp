package com.example.pokemonapp.ui.pokemonDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pokemonapp.base.BaseViewModel
import com.example.pokemonapp.common.enums.Status
import com.example.pokemonapp.model.Pokemon
import com.example.pokemonapp.model.PokemonResponse
import com.example.pokemonapp.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(private val repository: PokemonRepository): BaseViewModel() {

    private  val _pokemonDetail = MutableLiveData<PokemonResponse>()
    val pokemonDetail : LiveData<PokemonResponse> = _pokemonDetail

    private var _statusData = MutableLiveData<Status>()
    val statusData: LiveData<Status> = _statusData



    fun getPokemon(id: Int) {

        GlobalScope.launch {
            repository.getPokemonById(id)
                .catch {
                    _statusData.postValue(Status.ERROR)
                }
                .collect {
                    when (it.status) {
                        Status.LOADING -> {
                            _statusData.postValue(Status.LOADING)
                        }
                        Status.SUCCESS -> {
                            _pokemonDetail.postValue(it.data?.body())
                            _statusData.postValue(Status.SUCCESS)
                        }
                        Status.ERROR -> {
                            _statusData.postValue(Status.ERROR)
                        }
                    }
                }
        }
    }
}