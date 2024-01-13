package com.atom.searchcoffe.ui.reg

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atom.searchcoffe.data.network.ResponseState
import com.atom.searchcoffe.domain.dto.AuthResponse
import com.atom.searchcoffe.domain.dto.RegisterRequest
import com.atom.searchcoffe.domain.usecase.RegisterUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val sharedPreferences: SharedPreferences
) :
    ViewModel() {

    private val _registerState = MutableLiveData<ResponseState<AuthResponse>>()

    val registerState: LiveData<ResponseState<AuthResponse>> = _registerState

    private val _isEmailFieldValid = MutableLiveData<Boolean>()
    val isEmailFieldValid: LiveData<Boolean>
        get() = _isEmailFieldValid

    private val _isPasswordFieldValid = MutableLiveData<Boolean>()
    val isPasswordFieldValid: LiveData<Boolean>
        get() = _isPasswordFieldValid


    fun registerUser(request: RegisterRequest) {
        if (!validateInput(request.login, request.password)) {
            return
        }
        viewModelScope.launch {
            _registerState.value = ResponseState.Loading()
            try {
                val authResponse = registerUseCase.execute(request)
                saveToken(authResponse.token)
                _registerState.value = ResponseState.Success(authResponse)
            } catch (e: Exception) {
                _registerState.value = ResponseState.Error()
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