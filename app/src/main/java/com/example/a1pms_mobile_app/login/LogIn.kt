package com.example.a1pms_mobile_app.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.a1pms_mobile_app.R
import com.example.a1pms_mobile_app.databinding.FragmentLogInBinding
import com.example.a1pms_mobile_app.network.RetrofitProvider
import com.example.a1pms_mobile_app.network.User
import com.example.a1pms_mobile_app.view_model.LoginState
import com.example.a1pms_mobile_app.view_model.LoginViewModel
import com.example.a1pms_mobile_app.view_model.LoginViewModelFactory
import com.google.gson.Gson


class LogIn : Fragment() {

    private var _binding: FragmentLogInBinding? = null
    private val binding get() = _binding!!

    private val apiService = RetrofitProvider.getApiService()

    private val viewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(apiService)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLogInBinding.inflate(inflater, container, false)

        val token = SharedPrefsUtils.getToken(requireContext())
        if (!token.isNullOrEmpty()) {
            findNavController().navigate(R.id.action_logIn_to_dashboard)
        }

        viewModel.loginState.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is LoginState.Success -> {
                    SharedPrefsUtils.saveToken(requireContext(), state.token)
                    navigateToHome(state.token, state.user)
                }

                is LoginState.Error -> {
                    makeText(requireContext(), state.message, Toast.LENGTH_LONG).show()
                }

                else -> {}
            }
        })

        binding.btnLogIn.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            viewModel.loginUser(email, password)
        }

        binding.btnForgotPassword.setOnClickListener {
            makeText(requireContext(), "Clickable", Toast.LENGTH_LONG).show()
        }

        return binding.root
    }

    private fun navigateToHome(token: String, user: User) {
        val gson = Gson()
        val userJson = gson.toJson(user)
        val bundle = Bundle().apply {
            putString("TOKEN", token)
            putString("USER_JSON", userJson)
        }

        findNavController().navigate(R.id.action_logIn_to_dashboard, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

