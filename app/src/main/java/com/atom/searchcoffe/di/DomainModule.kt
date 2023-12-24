package com.atom.searchcoffe.di

import com.atom.searchcoffe.data.repository.RepositoryImpl
import com.atom.searchcoffe.domain.usecase.GetLocationUseCase
import com.atom.searchcoffe.domain.usecase.GetMenuUseCase
import com.atom.searchcoffe.domain.usecase.LoginUseCase
import com.atom.searchcoffe.domain.usecase.RegisterUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DomainModule {

    @Singleton
    @Provides
    fun provideRegisterUseCase(repository: RepositoryImpl): RegisterUseCase {
        return RegisterUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideLoginUseCase(repository: RepositoryImpl): LoginUseCase {
        return LoginUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetLocationUseCase(repository: RepositoryImpl): GetLocationUseCase {
        return GetLocationUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetMenuUseCase(repository: RepositoryImpl): GetMenuUseCase {
        return GetMenuUseCase(repository)
    }

}