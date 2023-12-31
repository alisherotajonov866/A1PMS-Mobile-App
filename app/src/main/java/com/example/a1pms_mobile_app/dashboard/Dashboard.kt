package com.example.a1pms_mobile_app.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.a1pms_mobile_app.R
import com.example.a1pms_mobile_app.databinding.FragmentDashboardBinding
import com.example.a1pms_mobile_app.view_model.UserDataViewModel

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

        }

        binding.ivProfile.setOnClickListener {
            findNavController().navigate(R.id.action_dashboard_to_profile)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

