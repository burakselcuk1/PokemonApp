package com.example.pokemonapp.ui.overlayFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.pokemonapp.R
import com.example.pokemonapp.base.BaseFragment
import com.example.pokemonapp.common.PermissionManager
import com.example.pokemonapp.databinding.FragmentOverlayBinding


class OverlayFragment : BaseFragment<FragmentOverlayBinding,OverlayViewModel>(
    R.layout.fragment_overlay,
    OverlayViewModel::class.java
) {
    override fun onInitDataBinding() {
    }

}
