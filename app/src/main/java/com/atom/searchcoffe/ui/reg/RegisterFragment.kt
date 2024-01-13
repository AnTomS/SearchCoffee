package com.atom.searchcoffe.ui.reg

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.atom.searchcoffe.App
import com.atom.searchcoffe.data.network.ResponseState
import com.atom.searchcoffe.databinding.FragmentRegisterBinding
import com.atom.searchcoffe.domain.dto.RegisterRequest
import com.atom.searchcoffe.ui.login.LoginFragmentDirections
import javax.inject.Inject

class RegisterFragment : Fragment() {


    private var _binding: FragmentRegisterBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var registerViewModel: RegisterViewModel

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
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        observeViewModel()
        addTextChangedListener()
        binding.btnSignUp.setOnClickListener {
            val login = binding.etMail.text?.trim().toString()
            val password = binding.etPassword.text?.trim().toString()
            val confirmPassword = binding.etPasswordConf.text?.trim().toString()

            if (login.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(requireActivity(), "Заполните все поля", Toast.LENGTH_LONG).show()
            } else if (password != confirmPassword) {
                Toast.makeText(requireActivity(), "Пароли не совпадают", Toast.LENGTH_LONG).show()
            } else {
                registerViewModel.registerUser(RegisterRequest(login, password))
            }
        }

        return binding.root

    }

    private fun observeViewModel() {
        registerViewModel.registerState.observe(viewLifecycleOwner) {
            when (it) {
                is ResponseState.Success -> {
                    goToMenu()
                }

                is ResponseState.Error -> {
                    Toast.makeText(
                        requireActivity(),
                        "Критическая ошибка. Попробуйте еще раз",
                        Toast.LENGTH_LONG
                    ).show()
                }

                is ResponseState.Loading -> {
                    Toast.makeText(requireActivity(), "Загрузка данных", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun addTextChangedListener() {
        binding.etMail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                registerViewModel.observeEmail()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        }
        )

        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                registerViewModel.observePassword()
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })


        binding.etPasswordConf.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                registerViewModel.isPasswordFieldValid
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