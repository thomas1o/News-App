package com.example.newsapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.data.News
import com.example.newsapp.data.adapter.NewsListAdapter
import com.example.newsapp.databinding.FragmentNewsBinding
import com.example.newsapp.viewmodel.NewsViewModel
import com.google.android.material.snackbar.Snackbar

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

        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]

        val searchBar = binding.searchBar

        viewModel.newsList.observe(viewLifecycleOwner, Observer { newsList ->
            setupRecyclerView(newsList)
        })

//        viewModel.numberOfItems.observe(viewLifecycleOwner, Observer { numberOfItems ->
//            binding.number.text = numberOfItems.toString()
//        })
//
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer { errorMessage ->
            Snackbar.make(requireView(), errorMessage, 5000).show()
        })

        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
//                viewModel.getNewsAndRefreshRecyclerView()
                return false
            }

            override fun onQueryTextChange(searchText: String?): Boolean {
                return false
            }

        })

        return binding.root
    }

    private fun setupRecyclerView(newsList: List<News>) {
        recyclerView = binding.newsList

        adapter = NewsListAdapter(newsList)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

}