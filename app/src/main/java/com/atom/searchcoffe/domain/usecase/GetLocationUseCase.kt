package com.atom.searchcoffe.domain.usecase

import com.atom.searchcoffe.data.repository.RepositoryImpl
import com.atom.searchcoffe.domain.dto.LocationRespondItem

class GetLocationUseCase(private val repository: RepositoryImpl) {

    suspend fun getCoffeeShops(): List<LocationRespondItem> = repository.getLocations()
}