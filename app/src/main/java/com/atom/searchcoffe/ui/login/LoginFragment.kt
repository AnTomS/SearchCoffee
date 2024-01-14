package com.atom.searchcoffe.ui.login

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.atom.searchcoffe.App
import com.atom.searchcoffe.R
import com.atom.searchcoffe.data.network.ResponseState
import com.atom.searchcoffe.databinding.FragmentLoginBinding
import com.atom.searchcoffe.domain.dto.LoginRequest
import javax.inject.Inject

class LoginFragment : Fragment() {


    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var loginViewModel: LoginViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val appComponent = (requireActivity().application as App).appComponent
        appComponent.inject(this)
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        observeViewModel()
        addTextChangedListener()
        binding.btnSignIn.setOnClickListener {
            val login = binding.etMail.text?.trim().toString()
            val password = binding.etPassword.text?.trim().toString()
            if (login.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireActivity(), "Заполните все поля", Toast.LENGTH_LONG).show()
            }
            loginViewModel.loginUser(LoginRequest(login, password))
        }


        binding.btnReg.setOnClickListener {
            goToRegister()
        }

        return binding.root

    }

    private fun goToRegister() {
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }

    private fun observeViewModel() {
        loginViewModel.loginState.observe(viewLifecycleOwner) {
            when (it) {
                is ResponseState.Success -> {
                    goToMenu()
                }

                is ResponseState.Error -> {
                    Toast.makeText(
                        requireActivity(),
                        "неверные данные для входа",
                        Toast.LENGTH_LONG
                    ).show()
                }

                is ResponseState.Loading -> {
                }
            }
        }
    }

    private fun addTextChangedListener() {
        binding.etMail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                loginViewModel.observeEmail()
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        }
        )

        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                loginViewModel.observePassword()
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    private fun goToMenu() {
        val action = LoginFragmentDirections.actionLoginFragmentToCoffeShopFragment()
        findNavController().navigate(action)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}