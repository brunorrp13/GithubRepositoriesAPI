package com.example.githubrepositories.domain.usecase

import com.example.githubrepositories.data.model.RepositoriesResponse
import com.example.githubrepositories.data.repository.GithubRepositoriesRepository
import com.example.githubrepositories.data.util.Resource

class GetRepositoriesUseCase(private val githubRepositoriesRepository: GithubRepositoriesRepository) {

    suspend fun execute(page : Int): Resource<RepositoriesResponse> {
        return githubRepositoriesRepository.getRepositories(page)
    }
}