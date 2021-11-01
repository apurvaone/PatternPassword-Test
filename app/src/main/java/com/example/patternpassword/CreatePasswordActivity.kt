package com.example.patternpassword

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.andrognito.patternlockview.PatternLockView
import android.content.Intent
import com.andrognito.patternlockview.PatternLockView.Dot

import com.andrognito.patternlockview.utils.PatternLockUtils

import com.andrognito.patternlockview.listener.PatternLockViewListener




class CreatePasswordActivity : AppCompatActivity() {

    lateinit var  mPatternLockView:PatternLockView
    lateinit var password:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_password)

        val sharedPreferences = getSharedPreferences("PREFS", 0)
        password= sharedPreferences.getString("password","0").toString()


        val mPatternLockView= findViewById<PatternLockView>(R.id.patternLockView)
        mPatternLockView.addPatternLockListener(object : PatternLockViewListener {
            override fun onStarted() {}
            override fun onProgress(progressPattern: List<Dot>) {}
            override fun onComplete(pattern: List<Dot>) {
                // Shared Preferences to save state
                val sharedPreferences = getSharedPreferences("PREFS", 0)
                val editor = sharedPreferences.edit()
                editor.putString(
                    "password",
                    PatternLockUtils.patternToString(mPatternLockView, pattern)
                )
                editor.apply()

                // Intent to navigate to home screen when password added is true
                val intent = Intent(applicationContext, ProgramActivity::class.java)
                startActivity(intent)
                finish()
            }

            override fun onCleared() {}
        })
    }
}