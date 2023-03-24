package com.example.pokemonapp.ui.overlayFragment

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemonapp.R
import com.example.pokemonapp.adapter.PokemonAdapter
import com.example.pokemonapp.base.BaseFragment
import com.example.pokemonapp.common.enums.Status
import com.example.pokemonapp.common.extensions.observe
import com.example.pokemonapp.common.extensions.observeEvent
import com.example.pokemonapp.common.tryOrLog
import com.example.pokemonapp.common.utils.ProgressDialogUtil
import com.example.pokemonapp.databinding.FragmentOverlayBinding


class PokemonFragment : BaseFragment<FragmentOverlayBinding,PokemonViewModel>(
    R.layout.fragment_overlay,
    PokemonViewModel::class.java
) {
    private lateinit var adapter: PokemonAdapter

    override fun onInitDataBinding() {
        getPokemon()
    }

    private fun getPokemon() {
        observeEvent(viewModel.statusData) {
            tryOrLog {
                when (it) {
                    Status.LOADING -> {
                        ProgressDialogUtil.showLoadingProgress(context = requireContext())
                        ProgressDialogUtil.start()
                    }
                    Status.SUCCESS -> {

                        binding.progressBar.visibility = View.GONE

                        observe(viewModel.pokemon){

                            adapter = PokemonAdapter(it)
                            binding.pokemonRecyclerView.layoutManager = LinearLayoutManager(context)
                            binding.pokemonRecyclerView.adapter = adapter

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
