package com.example.newsapp.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ExpandableListView.OnChildClickListener
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.data.News
import com.example.newsapp.databinding.NewsItemBinding

class NewsListAdapter (

    private val newsList: List<News>,
    private val clickListener: (News) -> Unit

): RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

    private lateinit var binding: NewsItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate(inflater, R.layout.news_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsListAdapter.ViewHolder, position: Int) {
        val news = newsList[position]
        holder.bind(news)
        holder.itemView.setOnClickListener {
            clickListener(newsList[position])
        }
        val animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.anim_fade_in)
        holder.itemView.startAnimation(animation)
    }

    override fun getItemCount() = newsList.size

    inner class ViewHolder(binding: NewsItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(news: News) {
            binding.news = news
            binding.executePendingBindings()
        }
    }

}