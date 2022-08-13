package com.example.githubrepositories.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RepositoriesResponse(
    @SerializedName("incomplete_results")
    var incompleteResults: Boolean,
    @SerializedName("items")
    var repositoryItems: List<RepositoryItem>,
    @SerializedName("total_count")
    var totalCount: Int
) : Serializable