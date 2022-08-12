package com.example.githubrepositories.data.repository

import com.example.githubrepositories.data.model.RepositoriesResponse
import com.example.githubrepositories.data.util.Resource

interface GithubRepositoriesRepository {

    suspend fun getRepositories(page: Int): Resource<RepositoriesResponse>

}