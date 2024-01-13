package com.atom.searchcoffe.di

import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import com.atom.searchcoffe.domain.usecase.GetLocationUseCase
import com.atom.searchcoffe.domain.usecase.LoginUseCase
import com.atom.searchcoffe.domain.usecase.RegisterUseCase
import com.atom.searchcoffe.ui.ViewModelFactory
import com.atom.searchcoffe.ui.coffeeshop.CoffeeShopViewModel
import com.atom.searchcoffe.ui.login.LoginViewModel
import com.atom.searchcoffe.ui.reg.RegisterViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ViewModelModule {

    @Singleton
    @Provides
    fun provideRegisterViewModelFactory(
        registerUseCase: RegisterUseCase,
        sharedPreferences: SharedPreferences
    ): ViewModelProvider.Factory {
        return ViewModelFactory { RegisterViewModel(registerUseCase, sharedPreferences) }
    }

    @Singleton
    @Provides
    fun provideLoginViewModelFactory(
        loginUseCase: LoginUseCase,
        sharedPreferences: SharedPreferences
    ): ViewModelProvider.Factory {
        return ViewModelFactory { LoginViewModel(loginUseCase, sharedPreferences) }
    }

    @Singleton
    @Provides
    fun provideCoffeeShopFactory(getLocationUseCase: GetLocationUseCase): ViewModelProvider.Factory {
        return ViewModelFactory { CoffeeShopViewModel(getLocationUseCase) }
    }


}