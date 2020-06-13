package com.example.loginregister

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_signup.*

class signup : AppCompatActivity() {

    public lateinit var handler: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        handler = DatabaseHelper(this)

        val txtwelcome: LinearLayout = findViewById(R.id.signup_page)

        save.setOnClickListener {
            handler.insertUserData(
                name_signup_page.text.toString(),
                email_signup_page.text.toString(),
                password_signup_page.text.toString()
            )
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}


