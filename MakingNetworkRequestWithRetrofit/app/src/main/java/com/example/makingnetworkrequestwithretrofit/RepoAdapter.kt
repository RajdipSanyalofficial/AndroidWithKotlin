package com.example.makingnetworkrequestwithretrofit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.makingnetworkrequestwithretrofit.databinding.RepoItemBinding

class RepoAdapter : RecyclerView.Adapter<RepoAdapter.ViewHolder>() {
    private var repos: List<Repo> = listOf()

    inner class ViewHolder(val binding: RepoItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RepoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.repoNameTextView.text = repos[position].name
    }

    override fun getItemCount() = repos.size

    fun submitList(repoList: List<Repo>) {
        repos = repoList
        notifyDataSetChanged()
    }
}