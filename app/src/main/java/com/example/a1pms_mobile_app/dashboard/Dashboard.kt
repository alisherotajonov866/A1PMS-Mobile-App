package com.example.a1pms_mobile_app.dashboard

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.fragment.app.activityViewModels
import com.example.a1pms_mobile_app.MainActivity
import com.example.a1pms_mobile_app.databinding.FragmentDashboardBinding
import com.example.a1pms_mobile_app.network.User
import com.example.a1pms_mobile_app.view_model.UserDataViewModel
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException

class Dashboard : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private val userDataViewModel: UserDataViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        userDataViewModel.userData.observe(viewLifecycleOwner) { user ->
            binding.tvDashboard.text = user?.name
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

