package com.example.loginregister

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    lateinit var handler: DatabaseHelper
    private val REQUEST_CODE = 15

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        handler = DatabaseHelper(this)

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                val intent = Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:$packageName")
                )
                startActivityForResult(intent, REQUEST_CODE)
            }
        }

        val btn_login: Button = findViewById(R.id.btn_login)
        btn_login.setOnClickListener {
            val intent = Intent(this, login::class.java)
            startActivity(intent)
        }

        val btn_signup: Button = findViewById(R.id.btn_signup)
        btn_signup.setOnClickListener {
            val intent = Intent(this, signup::class.java)
            startActivity(intent)
        }


    }
}
