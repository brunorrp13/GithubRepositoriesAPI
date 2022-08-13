package com.example.githubrepositories.ui.di

import android.app.Application
import com.example.githubrepositories.domain.usecase.GetRepositoriesUseCase
import com.example.githubrepositories.ui.viewmodel.RepositoriesViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {
    @Singleton
    @Provides
  fun provideRepositoriesViewModelFactory(
     application: Application,
     getRepositoriesUseCase: GetRepositoriesUseCase
  ):RepositoriesViewModelFactory{
      return RepositoriesViewModelFactory(
          application,
          getRepositoriesUseCase
      )
  }

}








