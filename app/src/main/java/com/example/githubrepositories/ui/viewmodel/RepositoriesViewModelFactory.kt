package com.example.githubrepositories.ui.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubrepositories.domain.usecase.GetRepositoriesUseCase

class RepositoriesViewModelFactory(
    private val app:Application,
    private val getRepositoriesUseCase: GetRepositoriesUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RepositoriesViewModel(
            app,
            getRepositoriesUseCase
        ) as T
    }
}









