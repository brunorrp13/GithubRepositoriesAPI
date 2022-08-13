package com.example.githubrepositories.ui.di

import com.example.githubrepositories.data.api.RepositoriesAPIService
import com.example.githubrepositories.data.repository.GithubRepositoriesRepository
import com.example.githubrepositories.domain.repository.GithubRepositoriesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideGithubRepositoriesRepository(
        repositoriesAPIService: RepositoriesAPIService
    ):GithubRepositoriesRepository{
       return GithubRepositoriesRepositoryImpl(repositoriesAPIService)
    }

}












