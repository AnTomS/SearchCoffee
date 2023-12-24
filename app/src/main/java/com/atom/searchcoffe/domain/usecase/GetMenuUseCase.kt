package com.atom.searchcoffe.domain.usecase

import com.atom.searchcoffe.data.repository.RepositoryImpl

class GetMenuUseCase(private val repository: RepositoryImpl) {
    suspend fun getMenu(locationId: Int): List<com.atom.searchcoffe.domain.dto.MenuItem> {
        return repository.getMenu(locationId)
    }
}