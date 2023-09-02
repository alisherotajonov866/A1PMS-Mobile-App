package com.example.a1pms_mobile_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.a1pms_mobile_app.databinding.FragmentProfileBinding
import com.example.a1pms_mobile_app.login.SharedPrefsUtils
import com.example.a1pms_mobile_app.network.RetrofitProvider
import com.example.a1pms_mobile_app.view_model.LoginViewModel
import com.example.a1pms_mobile_app.view_model.LoginViewModelFactory
import com.example.a1pms_mobile_app.view_model.UserDataViewModel

class Profile : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val userDataViewModel: UserDataViewModel by activityViewModels()

    private val viewModel: LoginViewModel by viewModels { LoginViewModelFactory(apiService) }

    private val apiService = RetrofitProvider.getApiService()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.btnLogOut.setOnClickListener {
            SharedPrefsUtils.clearToken(requireContext())
            userDataViewModel.clearUserData()
            viewModel.logout()
            findNavController().navigate(R.id.action_logout)
        }

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}