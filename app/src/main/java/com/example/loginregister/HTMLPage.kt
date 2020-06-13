package com.example.loginregister

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_htmlpage.*

class HTMLPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_htmlpage)
        htmlquiz.setOnClickListener {
            val intent = Intent(this, HTMLQuiz::class.java)
            startActivity(intent)
        }
    }
}
