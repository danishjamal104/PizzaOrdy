package com.github.danishjamal104.pizzaordy.di

import android.content.Context
import com.github.danishjamal104.pizzaordy.ui.PizzaOrdy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideBaseApplication(@ApplicationContext context: Context): PizzaOrdy {
        return context as PizzaOrdy
    }

}