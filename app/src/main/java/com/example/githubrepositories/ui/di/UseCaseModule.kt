package com.example.githubrepositories.ui.di

import com.example.githubrepositories.data.repository.GithubRepositoriesRepository
import com.example.githubrepositories.domain.usecase.GetRepositoriesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
   @Singleton
   @Provides
   fun provideGetRepositoriesUseCase(
      githubRepositoriesRepository: GithubRepositoriesRepository
   ): GetRepositoriesUseCase {
      return GetRepositoriesUseCase(githubRepositoriesRepository)
   }

}


















