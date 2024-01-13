package com.atom.searchcoffe.domain.usecase

import com.atom.searchcoffe.data.repository.RepositoryImpl
import com.atom.searchcoffe.domain.dto.Coffee

class GetMenuUseCase(private val repository: RepositoryImpl) {
    suspend fun getMenu(locationId: Int): List<Coffee> {
        return repository.getMenu(locationId)
    }
}