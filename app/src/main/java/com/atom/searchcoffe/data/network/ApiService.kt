package com.atom.searchcoffe.data.network

import com.atom.searchcoffe.domain.dto.AuthResponse
import com.atom.searchcoffe.domain.dto.LocationRespondItem
import com.atom.searchcoffe.domain.dto.LoginRequest
import com.atom.searchcoffe.domain.dto.MenuItem
import com.atom.searchcoffe.domain.dto.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {


    @POST("/auth/register")
    suspend fun register(@Body request: RegisterRequest): AuthResponse

    @POST("/auth/login")
    suspend fun login(@Body request: LoginRequest): AuthResponse

    @GET("/locations")
    suspend fun getLocations(): List<LocationRespondItem>

    @GET("/location/{id}/menu")
    suspend fun getMenu(@Path("id") locationId: Int): List<MenuItem>
}