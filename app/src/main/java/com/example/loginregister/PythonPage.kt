package com.example.loginregister

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_python_page.*

class PythonPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_python_page)

        pythonquiz.setOnClickListener {
            val intent = Intent(this, PythonQuiz::class.java)
            startActivity(intent)
        }
    }
}
