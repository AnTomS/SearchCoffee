package com.atom.searchcoffe

import android.app.Application
import android.util.Log
import com.atom.searchcoffe.di.AppComponent
import com.atom.searchcoffe.di.AppModule
import com.atom.searchcoffe.di.DaggerAppComponent
import com.atom.searchcoffe.di.DataModule
import com.atom.searchcoffe.di.NetworkModule
import com.atom.searchcoffe.di.ViewModelModule

class App : Application() {
    lateinit var appComponent : AppComponent

    @Suppress("DEPRECATION")
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .dataModule(DataModule(this))
            .viewModelModule(ViewModelModule())
            .networkModule(NetworkModule())
            .build()

        Log.d("App", "AppComponent initialized: $appComponent")
    }
}