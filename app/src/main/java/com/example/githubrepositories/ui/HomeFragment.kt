package com.example.githubrepositories.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubrepositories.R
import com.example.githubrepositories.data.model.RepoDetails
import com.example.githubrepositories.data.util.Resource
import com.example.githubrepositories.databinding.FragmentHomeBinding
import com.example.githubrepositories.ui.adapter.RepositoriesAdapter
import com.example.githubrepositories.ui.viewmodel.RepositoriesViewModel

class HomeFragment : androidx.fragment.app.Fragment() {

    private lateinit var fragmentHomeBinding: FragmentHomeBinding

    private lateinit var viewModel: RepositoriesViewModel
    private lateinit var repositoriesAdapter: RepositoriesAdapter
    private var page = 1
    private var isScrolling = false
    private var isLoading = false
    private var isLastPage = false
    private var pages = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentHomeBinding = FragmentHomeBinding.bind(view)

        viewModel = (activity as MainActivity).viewModel
        repositoriesAdapter = (activity as MainActivity).repositoriesAdapter
        initRecyclerView()
        viewRepositoryList()
        repositoriesAdapter.setOnItemClickListener {
            val details = RepoDetails(
                it.name,
                it.owner.login,
                it.description
            )
            val bundle = Bundle().apply {
                putSerializable("selected_repository", details)
            }
            findNavController().navigate(
                R.id.action_FirstFragment_to_SecondFragment,
                bundle
            )
        }
    }

    private fun viewRepositoryList() {
        viewModel.getGithubRepositories(page)
        viewModel.repositories.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        Log.i("MYTAG", "came here ${it.repositoryItems.toList().size}")
                        repositoriesAdapter.differ.submitList(it.repositoryItems.toList())
                        if (it.totalCount % 20 == 0) {
                            pages = it.totalCount / 20
                        } else {
                            pages = it.totalCount / 20 + 1
                        }
                        isLastPage = page == pages
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {
                        Toast.makeText(activity, "An error occurred : $it", Toast.LENGTH_LONG)
                            .show()
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        }
    }

    private fun showProgressBar() {
        isLoading = true
        fragmentHomeBinding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        isLoading = false
        fragmentHomeBinding.progressBar.visibility = View.INVISIBLE
    }

    private fun initRecyclerView() {
        // newsAdapter = NewsAdapter()
        fragmentHomeBinding.rvRepositories.apply {
            adapter = repositoriesAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@HomeFragment.onScrollListener)
        }
    }

    private val onScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }

        }
    }
}