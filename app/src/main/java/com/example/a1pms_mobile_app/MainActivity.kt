package com.example.a1pms_mobile_app

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.a1pms_mobile_app.databinding.ActivityMainBinding
import com.example.a1pms_mobile_app.view_model.UserDataViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var userDataViewModel: UserDataViewModel

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.myToolbar)
        binding.myToolbar.setTitleTextColor(R.color.md_theme_light_onPrimaryContainer)
        setUpBottomNavigation()

        userDataViewModel = ViewModelProvider(this)[UserDataViewModel::class.java]


    }

    private fun setUpBottomNavigation() {
        val navController = findNavController(R.id.fragmentContainerView)
        binding.bottomNavigationView.setupWithNavController(navController)

    }
}