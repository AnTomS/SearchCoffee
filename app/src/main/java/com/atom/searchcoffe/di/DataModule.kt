package com.atom.searchcoffe.di

import android.app.Application
import com.atom.searchcoffe.data.network.ApiService
import com.atom.searchcoffe.data.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
class DataModule(private val application: Application) {

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRepositoryImpl(
        apiService: ApiService
    ): RepositoryImpl {
        return RepositoryImpl(apiService)
    }
}