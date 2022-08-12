package com.example.githubrepositories.ui.di

import com.example.githubrepositories.data.api.RepositoriesAPIService
import com.example.githubrepositories.util.Constants.MY_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
         return Retrofit.Builder()
             .addConverterFactory(GsonConverterFactory.create())
             .baseUrl(MY_URL)
             .build()
    }

    @Singleton
    @Provides
    fun provideRepositoriesAPIService(retrofit: Retrofit): RepositoriesAPIService {
        return retrofit.create(RepositoriesAPIService::class.java)
    }

}













