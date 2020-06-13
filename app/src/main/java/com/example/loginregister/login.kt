package com.example.loginregister
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class login : AppCompatActivity() {
    lateinit var handler:DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        handler= DatabaseHelper(this)

        login_button.setOnClickListener{
            if(handler.userPresent(login_email.text.toString(),login_password.text.toString())) {
                val intent = Intent(this, dashboard :: class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this,"Invalid Entry",Toast.LENGTH_SHORT).show()
                startActivity(intent)}
        }
    }
}
