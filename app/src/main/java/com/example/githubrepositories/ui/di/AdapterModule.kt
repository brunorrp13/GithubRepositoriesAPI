package com.example.githubrepositories.ui.di

import com.example.githubrepositories.ui.adapter.RepositoriesAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {
   @Singleton
   @Provides
   fun provideRepositoriesAdapter():RepositoriesAdapter{
       return RepositoriesAdapter()
   }
}