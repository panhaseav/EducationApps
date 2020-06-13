package com.example.loginregister

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_java_page.*

class JavaPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_java_page)
        javaquiz.setOnClickListener {
            val intent = Intent(this, JavaQuiz::class.java)
            startActivity(intent)
        }
    }
}
