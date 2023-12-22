package com.atom.searchcoffe.data.repository

import com.atom.searchcoffe.domain.dto.AuthResponse
import com.atom.searchcoffe.domain.dto.LocationRespondItem
import com.atom.searchcoffe.domain.dto.LoginRequest
import com.atom.searchcoffe.domain.dto.MenuItem
import com.atom.searchcoffe.domain.dto.RegisterRequest
import com.atom.searchcoffe.domain.repository.Repository

class RepositoryImpl : Repository {
    override suspend fun register(request: RegisterRequest): AuthResponse {
        TODO("Not yet implemented")
    }

    override suspend fun login(request: LoginRequest): AuthResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getLocations(): List<LocationRespondItem> {
        TODO("Not yet implemented")
    }

    override suspend fun getMenu(locationId: Int): List<MenuItem> {
        TODO("Not yet implemented")
    }

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