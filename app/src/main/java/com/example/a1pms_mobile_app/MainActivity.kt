package com.example.a1pms_mobile_app

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
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

        setUpBottomNavigation()

        userDataViewModel = ViewModelProvider(this)[UserDataViewModel::class.java]

    }

    private fun setUpBottomNavigation() {
        val navController = findNavController(R.id.fragmentContainerView)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.logIn -> {
                    binding.bottomNavigationView.visibility = View.GONE
                }

                else -> {
                    binding.bottomNavigationView.visibility = View.VISIBLE
                    navController.graph.setStartDestination(R.id.dashboard)
                    binding.bottomNavigationView.setupWithNavController(navController)
                }
            }
        }

    }
}