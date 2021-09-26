package com.github.danishjamal104.pizzaordy.di

import android.content.Context
import com.github.danishjamal104.pizzaordy.BuildConfig
import com.github.danishjamal104.pizzaordy.data.mapper.pizzamapper.PizzaMapper
import com.github.danishjamal104.pizzaordy.data.remote.NetworkDataSource
import com.github.danishjamal104.pizzaordy.data.remote.interceptor.NetworkInterceptor
import com.github.danishjamal104.pizzaordy.data.remote.services.ApiService
import com.github.danishjamal104.pizzaordy.data.remote.NetworkDataSourceImpl
import com.github.danishjamal104.pizzaordy.data.remote.services.pizza.MockPizzaService
import com.github.danishjamal104.pizzaordy.data.remote.services.pizza.PizzaService
import com.github.danishjamal104.pizzaordy.data.remote.services.pizza.PizzaServiceImpl
import com.github.danishjamal104.pizzaordy.utils.AppConstants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()

    @Singleton
    @Provides
    fun provideNetworkInterceptor(@ApplicationContext context: Context): NetworkInterceptor {
        return NetworkInterceptor(context)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        networkInterceptor: NetworkInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(networkInterceptor).build()
    }

    @Singleton
    @Provides
    fun provideRetroFitBuilder(
        gson: Gson,
        client: OkHttpClient
    ): Retrofit.Builder {
        return Retrofit.Builder().baseUrl(AppConstants.BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideApiService(retrofitBuilder: Retrofit.Builder,): ApiService {
        return retrofitBuilder.build().create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun providePizzaService(apiService: ApiService,
                            gson: Gson): PizzaService {
        return if(BuildConfig.DEBUG) {
            MockPizzaService(gson)
        } else {
            PizzaServiceImpl(apiService)
        }
    }

    @Singleton
    @Provides
    fun provideNetworkDataSource(
        pizzaService: PizzaService,
        pizzaMapper: PizzaMapper
    ): NetworkDataSource {
        return NetworkDataSourceImpl(pizzaService, pizzaMapper)
    }


}