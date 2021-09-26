package com.github.danishjamal104.pizzaordy.di

import com.github.danishjamal104.pizzaordy.data.remote.NetworkDataSource
import com.github.danishjamal104.pizzaordy.data.repository.HomeRepository
import com.github.danishjamal104.pizzaordy.data.repository.HomeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideHomeRepository(networkDataSource: NetworkDataSource): HomeRepository {
        return HomeRepositoryImpl(networkDataSource)
    }
}