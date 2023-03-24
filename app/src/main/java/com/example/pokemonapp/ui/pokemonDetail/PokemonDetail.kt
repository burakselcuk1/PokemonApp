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
    @SuppressLint("MissingInflatedId")
    override fun onInitDataBinding() {
        val pokemonId = arguments?.getInt("pokemonId", -1) ?: -1


        viewModel.getPokemon(pokemonId)

        getDetailInformations()

        binding.button.setOnClickListener {
            activity?.finish()

            val mParams: WindowManager.LayoutParams? = WindowManager.LayoutParams(
                900,
                900,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                PixelFormat.TRANSLUCENT)

            val testView = LayoutInflater.from(context).inflate(R.layout.overlay_hello, null)

            val wm = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            wm.addView(testView, mParams)

            val overlayName = testView.findViewById<TextView>(R.id.overlay_name)
            val overlayImage = testView.findViewById<ImageView>(R.id.overlay_image)

            observe(viewModel.pokemonDetail) {
                overlayName.text = it.name.toString()
                Glide.with(this).load(it.sprites.frontDefault).into(overlayImage)

            }

            testView.findViewById<Button>(R.id.button2).setOnClickListener {
                activity?.finish()
                wm.removeView(testView)
            }
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