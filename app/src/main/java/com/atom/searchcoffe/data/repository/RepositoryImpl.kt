package com.atom.searchcoffe.data.repository

import com.atom.searchcoffe.data.network.ApiService
import com.atom.searchcoffe.domain.dto.AuthResponse
import com.atom.searchcoffe.domain.dto.LocationRespondItem
import com.atom.searchcoffe.domain.dto.LoginRequest
import com.atom.searchcoffe.domain.dto.MenuItem
import com.atom.searchcoffe.domain.dto.RegisterRequest
import com.atom.searchcoffe.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: ApiService) : Repository {
    override suspend fun register(request: RegisterRequest): AuthResponse =
        apiService.register(request)

    override suspend fun login(request: LoginRequest): AuthResponse =
        apiService.login(request)

    override suspend fun getLocations(): List<LocationRespondItem> =
        apiService.getLocations()

    override suspend fun getMenu(locationId: Int): List<MenuItem> =
        apiService.getMenu(locationId)

    override fun addToCart() {
        TODO("Not yet implemented")
    }

    override fun removeFromCart() {
        TODO("Not yet implemented")
    }

    override fun increaseDishQuantity() {
        TODO("Not yet implemented")
    }

    override fun decreaseDishQuantity() {
        TODO("Not yet implemented")
    }
}