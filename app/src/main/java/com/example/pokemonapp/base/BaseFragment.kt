package com.example.pokemonapp.base

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.pokemonapp.R
import com.example.pokemonapp.common.PermissionManager
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseFragment<DB : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes private val layoutId: Int,
    private val viewModelClass: Class<VM>
) : Fragment() {


    lateinit var viewModel: VM

    lateinit var binding: DB

    internal var disposable = CompositeDisposable()

    private lateinit var permissionManager: PermissionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity.let {
            viewModel = ViewModelProvider(it!!).get(viewModelClass)
        }
    }



    abstract fun onInitDataBinding()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        permissionManager = PermissionManager(requireActivity())
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)

        return binding.root
    }

    private fun checkInternetConnection() {
        lifecycleScope.launch(Dispatchers.IO) {
            requireActivity().runOnUiThread {
                if (permissionManager.isInternetAvailable(requireContext())) {
                    // İnternet bağlantısı var, yapılacak işlemler
                    Toast.makeText(requireContext(), "İnternet erişimi var", Toast.LENGTH_SHORT).show()
                } else {
                    // İnternet bağlantısı yok, mesaj göster
                    showNoInternetAccessView()

                }
        }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onInitDataBinding()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onResume() {
        super.onResume()

        checkInternetConnection()
    }

    private fun showNoInternetAccessView() {
        val noInternetAccessView = LayoutInflater.from(requireContext()).inflate(R.layout.no_internet_access, null) as ViewGroup
        (binding.root.parent as? ViewGroup)?.removeView(binding.root)
        Toast.makeText(requireContext(), "İnternet erişimi yok", Toast.LENGTH_SHORT).show()

        (binding.root.parent as? ViewGroup)?.addView(noInternetAccessView)
    }
}