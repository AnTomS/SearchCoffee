package com.atom.searchcoffe.domain.usecase

import com.atom.searchcoffe.data.repository.RepositoryImpl
import com.atom.searchcoffe.domain.dto.AuthResponse
import com.atom.searchcoffe.domain.dto.RegisterRequest

class RegisterUseCase(private val repository: RepositoryImpl) {
    suspend fun execute(request: RegisterRequest): AuthResponse {
        val registerData = repository.register(request)
        return registerData
    }
}