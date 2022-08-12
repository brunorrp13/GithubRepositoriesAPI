package com.example.githubrepositories.data.api

import com.example.githubrepositories.data.model.RepositoriesResponse
import com.example.githubrepositories.data.model.RepositoryItem
import com.example.githubrepositories.util.Constants.LANGUAGE_QUERY_PARAM
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoriesAPIService {

    @GET
    suspend fun getRepositories(
        @Query("language")
        language:String = LANGUAGE_QUERY_PARAM
    ): Response<RepositoriesResponse>

}