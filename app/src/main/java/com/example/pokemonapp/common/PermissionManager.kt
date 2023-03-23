package com.example.pokemonapp.common

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.pokemonapp.MainActivity
import com.example.pokemonapp.ui.permission.Permission

class PermissionManager(private val activity: Activity) {

    @RequiresApi(Build.VERSION_CODES.M)
    fun checkDrawOverlayPermission() {
        if (!Settings.canDrawOverlays(activity)) {
            val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:${activity.packageName}"))
            activity.startActivityForResult(intent, 1)
        } else {
            val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
            val isPermissionGranted = sharedPref.getBoolean("isPermissionGranted", false)
            if (isPermissionGranted) {
                startMainActivity()
            } else {
                startPermissionActivity()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun onPermissionResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1 && Settings.canDrawOverlays(activity)) {
            // İzin verildiğinde yapılacaklar
            val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
            with(sharedPref.edit()) {
                putBoolean("isPermissionGranted", true)
                apply()
            }
            startMainActivity()
        } else {
            Toast.makeText(activity, "Lütfen izin veriniz", Toast.LENGTH_SHORT).show()
        }
    }

    private fun startPermissionActivity() {
        val intent = Intent(activity, Permission::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        activity.startActivity(intent)
    }

    private fun startMainActivity() {
        val intent = Intent(activity, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        activity.startActivity(intent)
    }

    fun isPermissionGranted(): Boolean {
        return Settings.canDrawOverlays(activity)
    }

    fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val activeNetwork =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }
    }
}
