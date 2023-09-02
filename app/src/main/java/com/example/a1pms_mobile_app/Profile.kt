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
import com.example.a1pms_mobile_app.network.RetrofitProvider
import com.example.a1pms_mobile_app.network.User
import com.example.a1pms_mobile_app.preference.SharedPrefsUtils
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

        userDataViewModel.userData.observe(viewLifecycleOwner) { user ->
            user?.let { setData(it) }
        }

        binding.btnLogOut.setOnClickListener {
            SharedPrefsUtils.clearToken(requireContext())
            SharedPrefsUtils.clearUserDataFromPrefs(requireContext())
            userDataViewModel.clearUserData()
            viewModel.logout()
            findNavController().navigate(R.id.action_logout)
        }

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

    private fun setData(user: User) {
        binding.apply {
            tvFirstLastName.text = user.name
            tvEmail.text = user.email
            tvPhoneNumber.text = user.phoneNumber
            tvJobType.text = user.organization.name
            tvStir.text = user.organization.stir
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}