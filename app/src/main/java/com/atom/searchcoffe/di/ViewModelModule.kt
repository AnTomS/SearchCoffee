package com.atom.searchcoffe.di

import androidx.lifecycle.ViewModelProvider
import com.atom.searchcoffe.domain.usecase.GetLocationUseCase
import com.atom.searchcoffe.domain.usecase.GetMenuUseCase
import com.atom.searchcoffe.domain.usecase.LoginUseCase
import com.atom.searchcoffe.domain.usecase.RegisterUseCase
import com.atom.searchcoffe.ui.ViewModelFactory
import com.atom.searchcoffe.ui.coffeeshop.CoffeeShopViewModel
import com.atom.searchcoffe.ui.login.LoginViewModel
import com.atom.searchcoffe.ui.menu.MenuViewModel
import com.atom.searchcoffe.ui.reg.RegisterViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ViewModelModule {

    @Singleton
    @Provides
    fun provideRegisterViewModelFactory(registerUseCase: RegisterUseCase): ViewModelProvider.Factory {
        return ViewModelFactory { RegisterViewModel(registerUseCase) }
    }

    @Singleton
    @Provides
    fun provideLoginViewModelFactory(loginUseCase: LoginUseCase): ViewModelProvider.Factory {
        return ViewModelFactory { LoginViewModel(loginUseCase) }
    }

    @Singleton
    @Provides
    fun provideCoffeeShopFactory(getLocationUseCase: GetLocationUseCase): ViewModelProvider.Factory {
        return ViewModelFactory { CoffeeShopViewModel(getLocationUseCase) }
    }



}