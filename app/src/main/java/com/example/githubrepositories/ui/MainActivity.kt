package com.example.githubrepositories.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.githubrepositories.R
import com.example.githubrepositories.databinding.ActivityMainBinding
import com.example.githubrepositories.ui.adapter.RepositoriesAdapter
import com.example.githubrepositories.ui.viewmodel.RepositoriesViewModel
import com.example.githubrepositories.ui.viewmodel.RepositoriesViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: RepositoriesViewModelFactory
    @Inject
    lateinit var repositoriesAdapter: RepositoriesAdapter
    lateinit var viewModel: RepositoriesViewModel

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, factory).get(RepositoriesViewModel::class.java)

    }

}