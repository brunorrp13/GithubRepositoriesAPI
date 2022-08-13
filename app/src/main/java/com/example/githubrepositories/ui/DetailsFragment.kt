package com.example.githubrepositories.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.githubrepositories.R
import com.example.githubrepositories.data.model.RepoDetails
import com.example.githubrepositories.data.model.RepositoryItem
import com.example.githubrepositories.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private lateinit var fragmentDetailsBinding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentDetailsBinding = FragmentDetailsBinding.bind(view)

        val args: DetailsFragmentArgs by navArgs()
        val repository = args.selectedRepository
        fillInfo(repository)
        setOnBackButton()
    }

    private fun setOnBackButton() {
        fragmentDetailsBinding.buttonSecond.setOnClickListener {
            findNavController().navigate(
                R.id.action_SecondFragment_to_FirstFragment
            )
        }
    }

    private fun fillInfo(repository: RepoDetails) {
        fragmentDetailsBinding.name.text = repository.name
        fragmentDetailsBinding.ownerName.text = repository.owner
        fragmentDetailsBinding.details.text = repository.description
    }

}
