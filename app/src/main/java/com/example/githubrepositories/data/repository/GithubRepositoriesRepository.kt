package com.example.githubrepositories.data.repository

import com.example.githubrepositories.data.api.RepositoriesAPIService
import com.example.githubrepositories.data.util.Resource

interface GithubRepositoriesRepository {

    suspend fun getRepositories(user : String, page : Int): Resource<RepositoriesAPIService>

}