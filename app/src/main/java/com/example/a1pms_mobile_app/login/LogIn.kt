package com.example.a1pms_mobile_app.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.a1pms_mobile_app.R
import com.example.a1pms_mobile_app.databinding.FragmentLogInBinding
import com.example.a1pms_mobile_app.network.RetrofitProvider
import com.example.a1pms_mobile_app.network.User
import com.example.a1pms_mobile_app.view_model.LoginState
import com.example.a1pms_mobile_app.view_model.LoginViewModel
import com.example.a1pms_mobile_app.view_model.LoginViewModelFactory
import com.example.a1pms_mobile_app.view_model.UserDataViewModel
import com.google.gson.Gson


class LogIn : Fragment() {

    private var _binding: FragmentLogInBinding? = null
    private val binding get() = _binding!!

    private val apiService = RetrofitProvider.getApiService()

    private val viewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(apiService)
    }

    private val userDataViewModel: UserDataViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLogInBinding.inflate(inflater, container, false)

        val token = SharedPrefsUtils.getToken(requireContext())
        if (!token.isNullOrEmpty()) {
            findNavController().navigate(R.id.dashboard)
        }

        viewModel.loginState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is LoginState.Success -> {
                    SharedPrefsUtils.saveToken(requireContext(), state.token)
                    userDataViewModel.setUserData(state.user)
                    navigateToHome()
                }

                is LoginState.Error -> {
                    state.message?.let { toastMethod(it) }
                }

                else -> {
                    toastMethod("Oops!")
                }
            }
        }

        binding.btnLogIn.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            viewModel.loginUser(email, password)
        }

        binding.btnForgotPassword.setOnClickListener {
            toastMethod("Clickable")
        }

        return binding.root
    }

    private fun navigateToHome() {
        findNavController().navigate(R.id.action_logIn_to_dashboard)
    }

    private fun toastMethod(message: String) {
        makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

