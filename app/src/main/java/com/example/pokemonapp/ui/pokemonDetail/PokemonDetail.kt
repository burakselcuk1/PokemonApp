package com.example.pokemonapp.ui.pokemonDetail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pokemonapp.R
import com.example.pokemonapp.base.BaseFragment
import com.example.pokemonapp.databinding.FragmentMainPageBinding
import dagger.hilt.android.lifecycle.HiltViewModel


class PokemonDetail : BaseFragment<FragmentMainPageBinding, PokemonDetailViewModel>(
    layoutId = R.layout.fragment_main_page,
    viewModelClass = PokemonDetailViewModel::class.java
) {
    override fun onInitDataBinding() {
        val args = this.arguments
        val userId: String? = args?.getString("pokemonId","")


        binding.a.text = userId.toString()


        Log.e("burak", userId.toString())
    }


}