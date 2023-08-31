package com.example.a1pms_mobile_app.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.makeText
import com.example.a1pms_mobile_app.databinding.FragmentDashboardBinding
import com.example.a1pms_mobile_app.network.User
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException

class Dashboard : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        try {
            val gson = Gson()
            val userJson = arguments?.getString("USER_JSON")
            val user = gson.fromJson(userJson, User::class.java)
            if (user != null) {
                binding.tvDashboard.text = user.name
            } else {
                makeText(requireContext(), "Null data", Toast.LENGTH_LONG).show()
            }
        } catch (e: JsonSyntaxException) {
            makeText(requireContext(), "Problem with get data!", Toast.LENGTH_LONG).show()
        }



        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

