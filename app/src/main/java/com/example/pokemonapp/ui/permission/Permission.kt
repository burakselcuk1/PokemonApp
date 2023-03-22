package com.example.pokemonapp.ui.permission

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import com.example.pokemonapp.MainActivity
import com.example.pokemonapp.R
import com.example.pokemonapp.common.PermissionManager

class Permission : AppCompatActivity() {

    private lateinit var permissionManager: PermissionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission)

        permissionManager = PermissionManager(this)
        val permission = findViewById<Button>(R.id.permission)


        if (permissionManager.isPermissionGranted()) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        permission.setOnClickListener {
            permissionManager.checkDrawOverlayPermission()
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        permissionManager.onPermissionResult(requestCode, resultCode, data)
    }
}