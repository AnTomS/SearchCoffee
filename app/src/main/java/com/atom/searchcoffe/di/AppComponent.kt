package com.atom.searchcoffe.di

import android.util.Log
import com.atom.searchcoffe.MainActivity
import com.atom.searchcoffe.data.repository.RepositoryImpl
import com.atom.searchcoffe.ui.cart.CartFragment
import com.atom.searchcoffe.ui.coffeeshop.CoffeeShopFragment
import com.atom.searchcoffe.ui.login.LoginFragment
import com.atom.searchcoffe.ui.menu.MenuFragment
import com.atom.searchcoffe.ui.reg.RegisterFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, DataModule::class, NetworkModule::class, ViewModelModule::class, DomainModule::class])
interface AppComponent {

    fun inject(fragment: LoginFragment) {
        Log.d("AppComponent", "LoginFragment")
    }

    fun inject(fragment: RegisterFragment) {
        Log.d("AppComponent", "RegisterFragment")
    }

    fun inject(fragment: CoffeeShopFragment) {
        Log.d("AppComponent", "ListOfCoffeeShopFragment")
    }

    fun inject(fragment: MenuFragment) {
        Log.d("AppComponent", "MenuFragment")
    }

    fun inject(fragment: CartFragment) {
        Log.d("AppComponent", "CartFragment")
    }

    fun inject(activity: MainActivity) {
        Log.d("AppComponent", "Injecting MainActivity")
    }

    fun provideRepositoryImpl(): RepositoryImpl

}