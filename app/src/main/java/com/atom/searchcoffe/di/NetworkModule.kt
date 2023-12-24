package com.atom.searchcoffe.di

import android.content.SharedPreferences
import com.atom.searchcoffe.data.network.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named


@Module
class NetworkModule {

    companion object {
        private const val BASE_URL = "http://147.78.66.203:3210/"
    }

    @Provides
    fun provideAuthInterceptor(@Named("SharedForToken") sharedPreferences: SharedPreferences): Interceptor {
        return Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer " + provideToken(sharedPreferences))
                .build()
            chain.proceed(request)
        }
    }

    @Provides
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideToken(sharedPreferences: SharedPreferences): String {
        // Получение токена из SharedPreferences
        return sharedPreferences.getString("auth_token", "") ?: ""
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}