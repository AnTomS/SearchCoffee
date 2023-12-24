package com.atom.searchcoffe.domain.usecase

import com.atom.searchcoffe.data.repository.RepositoryImpl
import com.atom.searchcoffe.domain.dto.AuthResponse
import com.atom.searchcoffe.domain.dto.LoginRequest

class LoginUseCase(private val repository: RepositoryImpl) {

    suspend fun execute(request: LoginRequest): AuthResponse {
        val loginData = repository.login(request)
        return loginData
    }
}