package com.atom.searchcoffe.ui.login

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atom.searchcoffe.data.network.ResponseState
import com.atom.searchcoffe.domain.dto.AuthResponse
import com.atom.searchcoffe.domain.dto.LoginRequest
import com.atom.searchcoffe.domain.usecase.LoginUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val _loginState = MutableLiveData<ResponseState<AuthResponse>>()
    val loginState: LiveData<ResponseState<AuthResponse>> = _loginState

    private val _isEmailFieldValid = MutableLiveData<Boolean>()
    val isEmailFieldValid: LiveData<Boolean>
        get() = _isEmailFieldValid

    private val _isPasswordFieldValid = MutableLiveData<Boolean>()
    val isPasswordFieldValid: LiveData<Boolean>
        get() = _isPasswordFieldValid

    fun loginUser(request: LoginRequest) {
        if (!validateInput(request.login, request.password)) {
            return
        }
        viewModelScope.launch {
            _loginState.value = ResponseState.Loading()
            _loginState.value = try {
                ResponseState.Success(loginUseCase.execute(request))
            } catch (e: Exception) {
                ResponseState.Error()
            }
        }
    }

    private fun saveToken(token: String) {
        sharedPreferences.edit().putString(TOKEN_KEY, token).apply()
    }

    private fun validateInput(login: String, password: String): Boolean {
        _isEmailFieldValid.value = login.isNotEmpty()
        _isPasswordFieldValid.value = password.isNotEmpty()
        return !(isEmailFieldValid.value == false || isPasswordFieldValid.value == false)
    }

    fun observeEmail() {
        _isEmailFieldValid.value = true
    }

    fun observePassword() {
        _isPasswordFieldValid.value = true
    }

    companion object {
        private const val TOKEN_KEY = "token"
    }
}