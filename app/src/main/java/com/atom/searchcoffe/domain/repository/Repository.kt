package com.atom.searchcoffe.domain.repository

import com.atom.searchcoffe.domain.dto.AuthResponse
import com.atom.searchcoffe.domain.dto.Coffee
import com.atom.searchcoffe.domain.dto.LocationRespondItem
import com.atom.searchcoffe.domain.dto.LoginRequest
import com.atom.searchcoffe.domain.dto.RegisterRequest

interface Repository {
    suspend fun register(request: RegisterRequest): AuthResponse

    suspend fun login(request: LoginRequest): AuthResponse

    suspend fun getLocations(): List<LocationRespondItem>

    suspend fun getMenu(locationId: Int): List<Coffee>

    fun addToCart()
    fun removeFromCart()
    fun increaseDishQuantity()
    fun decreaseDishQuantity()
}