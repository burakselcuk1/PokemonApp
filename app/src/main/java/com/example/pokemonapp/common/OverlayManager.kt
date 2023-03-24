package com.example.pokemonapp.common

import android.app.Activity
import android.content.Context
import android.graphics.PixelFormat
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.example.pokemonapp.R
import com.example.pokemonapp.ui.pokemonDetail.PokemonDetailViewModel


class OverlayManager(private val context: Context, private val viewModel: PokemonDetailViewModel, private val activity: Activity?, private val owner: LifecycleOwner) {
    private var testView: View? = null
    private var windowManager: WindowManager? = null

    fun showOverlay() {
        val mParams: WindowManager.LayoutParams? = WindowManager.LayoutParams(
            900,
            900,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
            PixelFormat.TRANSLUCENT)

        testView = LayoutInflater.from(context).inflate(R.layout.overlay_hello, null)

        windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowManager?.addView(testView, mParams)

        val overlayName = testView?.findViewById<TextView>(R.id.overlay_name)
        val overlayImage = testView?.findViewById<ImageView>(R.id.overlay_image)

        viewModel.pokemonDetail.observe(owner) { pokemon ->
            overlayName?.text = pokemon.name
            Glide.with(context).load(pokemon.sprites.frontDefault).into(overlayImage!!)
        }

        testView?.findViewById<Button>(R.id.button2)?.setOnClickListener {
            activity?.finish()
            windowManager?.removeView(testView)
        }
    }

    fun hideOverlay() {
        windowManager?.removeView(testView)
    }
}
