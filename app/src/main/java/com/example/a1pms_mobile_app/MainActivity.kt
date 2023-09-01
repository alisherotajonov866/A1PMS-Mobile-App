package com.example.a1pms_mobile_app

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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


        binding.apply {
            setSupportActionBar(myToolbar)
            myToolbar.setTitleTextColor(R.color.md_theme_light_onPrimaryContainer)
            setUpBottomNavigation()

            tvProfile.setOnClickListener{
                goToProfile()
            }

            ivProfile.setOnClickListener{
                goToProfile()
            }
        }

        userDataViewModel = ViewModelProvider(this)[UserDataViewModel::class.java]

    }

    private fun goToProfile() {
        findNavController(R.id.fragmentContainerView).navigate(R.id.profile)
    }

    private fun setUpBottomNavigation() {
        val navController = findNavController(R.id.fragmentContainerView)
        binding.bottomNavigationView.setupWithNavController(navController)

    }
}