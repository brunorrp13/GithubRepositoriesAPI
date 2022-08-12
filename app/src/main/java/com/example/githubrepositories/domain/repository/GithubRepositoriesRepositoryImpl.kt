package com.example.githubrepositories.domain.repository

import com.example.githubrepositories.data.api.RepositoriesAPIService
import com.example.githubrepositories.data.model.RepositoriesResponse
import com.example.githubrepositories.data.repository.GithubRepositoriesRepository
import com.example.githubrepositories.data.util.Resource
import retrofit2.Response

class GithubRepositoriesRepositoryImpl(
    private val repositoriesAPIService: RepositoriesAPIService
) : GithubRepositoriesRepository {

    override suspend fun getRepositories(page: Int): Resource<RepositoriesResponse> {
        return responseToResource(repositoriesAPIService.getRepositories())
    }

    private fun responseToResource(response: Response<RepositoriesResponse>):Resource<RepositoriesResponse>{
        if(response.isSuccessful){
            response.body()?.let {result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

}
