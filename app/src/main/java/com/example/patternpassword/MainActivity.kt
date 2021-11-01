package com.example.patternpassword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.content.Intent

import android.content.SharedPreferences




class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val handler = Handler()
        handler.postDelayed({
            // loading is given
            val sharedPreferences = getSharedPreferences("PREFS", 0)
            val password = sharedPreferences.getString("password", "0")
            if (password == "0") {
                // Intent to navigate to Create Password Screen
                val intent = Intent(applicationContext, CreatePasswordActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // Intent to navigate to Input Password Screen
                val intent = Intent(applicationContext, InputPasswordActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 2000)


    }
}