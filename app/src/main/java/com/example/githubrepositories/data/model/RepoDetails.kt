package com.example.githubrepositories.data.model

import java.io.Serializable

data class RepoDetails (
    var name: String,
    var owner: String,
    var description: String
) : Serializable