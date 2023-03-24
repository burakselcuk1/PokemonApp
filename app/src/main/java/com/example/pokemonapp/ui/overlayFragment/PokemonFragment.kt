package com.example.pokemonapp.ui.overlayFragment

import com.example.pokemonapp.R
import com.example.pokemonapp.base.BaseFragment
import com.example.pokemonapp.databinding.FragmentOverlayBinding


class PokemonFragment : BaseFragment<FragmentOverlayBinding,PokemonViewModel>(
    R.layout.fragment_overlay,
    PokemonViewModel::class.java
) {
    override fun onInitDataBinding() {

    }
}
