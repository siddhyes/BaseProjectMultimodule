package com.nst.dhanam.feature_welcome.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.nst.dhanam.databinding.ActivityWelcomeBinding
import com.nst.feature_auth.ui.AuthActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding :ActivityWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Handler(Looper.myLooper()!!).postDelayed({
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
        }, 200)
    }
}
