package com.example.pokemonapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        val exitMenuItem = menu.findItem(R.id.menu_exit)
        exitMenuItem.setOnMenuItemClickListener {
            finishAffinity()
            true
        }

        return true
    }

}