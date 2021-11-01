package com.example.patternpassword

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.andrognito.patternlockview.PatternLockView
import com.andrognito.patternlockview.PatternLockView.Dot
import com.andrognito.patternlockview.listener.PatternLockViewListener
import com.andrognito.patternlockview.utils.PatternLockUtils


class InputPasswordActivity : AppCompatActivity() {
    var mPatternLockView: PatternLockView? = null
    var password: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_password)

        // shared preference when user comes second time to the app
        val sharedPreferences = getSharedPreferences("PREFS", 0)
        password = sharedPreferences.getString("password", "0")
        mPatternLockView = findViewById<View>(R.id.pattern_lock_view) as PatternLockView
        mPatternLockView!!.addPatternLockListener(object : PatternLockViewListener {
            override fun onStarted() {}
            override fun onProgress(progressPattern: List<Dot>) {}
            override fun onComplete(pattern: List<Dot>) {
                // if drawn pattern is equal to created pattern you will navigate to home screen
                if (password == PatternLockUtils.patternToString(mPatternLockView, pattern)) {
                    val intent = Intent(applicationContext, ProgramActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // other wise you will get error wrong password
                    Toast.makeText(this@InputPasswordActivity, "Wrong Password", Toast.LENGTH_SHORT)
                        .show()
                    mPatternLockView!!.clearPattern()
                }
            }

            override fun onCleared() {}
        })
    }
}
