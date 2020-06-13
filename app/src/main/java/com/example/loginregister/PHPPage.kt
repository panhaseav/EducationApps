package com.example.loginregister

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_phppage.*
import kotlinx.android.synthetic.main.activity_python_page.*

class PHPPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phppage)
        phpquiz.setOnClickListener {
            val intent = Intent(this, PHPQuiz::class.java)
            startActivity(intent)
        }
    }
}
