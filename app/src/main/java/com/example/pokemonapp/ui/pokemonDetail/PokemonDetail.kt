package com.example.pokemonapp.ui.pokemonDetail

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.PixelFormat
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.pokemonapp.R
import com.example.pokemonapp.base.BaseFragment
import com.example.pokemonapp.common.OverlayManager
import com.example.pokemonapp.common.enums.Status
import com.example.pokemonapp.common.extensions.observe
import com.example.pokemonapp.common.extensions.observeEvent
import com.example.pokemonapp.common.tryOrLog
import com.example.pokemonapp.common.utils.ProgressDialogUtil
import com.example.pokemonapp.databinding.FragmentMainPageBinding
import dagger.hilt.android.lifecycle.HiltViewModel


class PokemonDetail : BaseFragment<FragmentMainPageBinding, PokemonDetailViewModel>(
    layoutId = R.layout.fragment_main_page,
    viewModelClass = PokemonDetailViewModel::class.java
) {

    override fun onInitDataBinding() {
        val pokemonId = arguments?.getInt("pokemonId", -1) ?: -1


        viewModel.getPokemon(pokemonId)

        getDetailInformations()

        binding.button.setOnClickListener {
            activity?.finish()
            val overlayManager = OverlayManager(requireContext(), viewModel, requireActivity(), viewLifecycleOwner)
            overlayManager.showOverlay()
        }
    }


    private fun getDetailInformations() {
        observeEvent(viewModel.statusData) {
            tryOrLog {
                when (it) {
                    Status.LOADING -> {
                        ProgressDialogUtil.showLoadingProgress(context = requireContext())
                        ProgressDialogUtil.start()
                    }
                    Status.SUCCESS -> {

                        observe(viewModel.pokemonDetail){

                             binding.pokemonDetailName.text = it.name.toString()
                            Glide.with(this).load(it.sprites.frontDefault).into(binding.imageView)

                        }
                        ProgressDialogUtil.stop()
                    }
                    Status.ERROR -> {
                        ProgressDialogUtil.stop()
                    }
                }
            }
        }
    }
}