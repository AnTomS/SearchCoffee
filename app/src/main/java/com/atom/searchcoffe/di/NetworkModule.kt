package com.atom.searchcoffe.di

import android.content.SharedPreferences
import android.util.Log
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
class NetworkModule {

    companion object {
        private const val TOKEN_KEY = "token"
        private const val BASE_URL = "http://147.78.66.203:3210/"
    }

    @Provides
    fun provideAuthInterceptor(sharedPreferences: SharedPreferences): Interceptor {
        return Interceptor { chain ->
            val token = sharedPreferences.getString("auth_token", "") ?: ""
            Log.d("NetworkModule", "Using token: $token")
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
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
        val token = sharedPreferences.getString(TOKEN_KEY, "") ?: ""
        Log.d("NetworkModule", "Providing token: $token")
        return token
    }

}