package com.example.newsapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.data.News
import com.example.newsapp.data.adapter.NewsListAdapter
import com.example.newsapp.databinding.FragmentNewsBinding
import com.example.newsapp.viewmodel.NewsViewModel

class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding
    private lateinit var viewModel: NewsViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NewsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_news, container, false
        )

        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        val newsList = listOf(
            News(
                title = "First News",
                description = "This is the first news item in the list.",
                language = "English"
            ),
            News(
                title = "Second News",
                description = "This is the second news item in the list.",
                language = "English"
            ),
            News(
                title = "Third News",
                description = "This is the third news item in the list.",
                language = "English"
            ),News(
                title = "Third News",
                description = "This is the third news item in the list.",
                language = "English"
            ),News(
                title = "Third News",
                description = "This is the third news item in the list.",
                language = "English"
            ),News(
                title = "Third News",
                description = "This is the third news item in the list.",
                language = "English"
            ),News(
                title = "Third News",
                description = "This is the third news item in the list.",
                language = "English"
            ),News(
                title = "Third News",
                description = "This is the third news item in the list.",
                language = "English"
            ),News(
                title = "Third News",
                description = "This is the third news item in the list.",
                language = "English"
            ),News(
                title = "Third News",
                description = "This is the third news item in the list.",
                language = "English"
            )
        )

        setupRecyclerView(newsList)

        return binding.root
    }

    private fun setupRecyclerView(newsList: List<News>) {
        recyclerView = binding.newsList

        adapter = NewsListAdapter(newsList)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

}