package com.example.githubrepositories.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.githubrepositories.data.model.RepositoryItem
import com.example.githubrepositories.databinding.RepositoryItemBinding

class RepositoriesAdapter: RecyclerView.Adapter<RepositoriesAdapter.RepositoryViewHolder>()  {

    private val callback = object : DiffUtil.ItemCallback<RepositoryItem>(){
        override fun areItemsTheSame(oldItem: RepositoryItem, newItem: RepositoryItem): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: RepositoryItem, newItem: RepositoryItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,callback)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RepositoriesAdapter.RepositoryViewHolder {
        val binding = RepositoryItemBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        return RepositoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoriesAdapter.RepositoryViewHolder, position: Int) {
        val repository = differ.currentList[position]
        holder.bind(repository)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class RepositoryViewHolder(
        val binding:RepositoryItemBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(repositoryItem: RepositoryItem){
            binding.repositoryName.text = repositoryItem.name
            binding.repositoryAuthor.text = repositoryItem.owner?.login

            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(repositoryItem)
                }
            }
        }
    }

    private var onItemClickListener :((RepositoryItem)->Unit)?=null

    fun setOnItemClickListener(listener : (RepositoryItem)->Unit){
        onItemClickListener = listener
    }


}