package com.nst.feature_auth.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.nst.feature_auth.databinding.ActivityAuthBinding
import com.nst.module_navigation.Activities
import com.nst.module_navigation.Navigator

import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {


    @Inject
    lateinit var provider: Navigator.Provider
    private lateinit var binding: ActivityAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.login.setOnClickListener {
            provider.getActivities(Activities.CollectionDashBoardActivity).navigate(this)
            finish()
        }
    }
}