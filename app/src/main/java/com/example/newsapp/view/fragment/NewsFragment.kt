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
    ): View {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_news, container, false
        )

        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]

        viewModel.newsList.observe(viewLifecycleOwner, Observer { newsList ->
            setupRecyclerView(newsList)
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer { errorMessage ->
            Snackbar.make(requireView(), errorMessage, 5000).show()
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startAnimation()

        val searchBar = binding.searchBar
        val swipeRefreshLayout = binding.swipeRefreshLayout

        viewModel.loadingFinished.observe(viewLifecycleOwner, Observer { loadingFinished ->
            if(loadingFinished == false)
                startAnimation()
            else
                stopAnimation()
        })

        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    viewModel.getNewsUsingKeyword(query)
                }
                return true
            }

            override fun onQueryTextChange(searchText: String?): Boolean {
                return false
            }
        })

        swipeRefreshLayout.setOnRefreshListener {
            startAnimation()
            viewModel.getNews()
            swipeRefreshLayout.isRefreshing = false
        }

    }

    private fun setupRecyclerView(newsList: List<News>) {
        recyclerView = binding.newsList

        adapter = NewsListAdapter(newsList)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

    private fun startAnimation() {
        binding.shimmerLayout.startShimmer();
        binding.shimmerLayout.visibility = View.VISIBLE;
    }

    private fun stopAnimation() {
        binding.shimmerLayout.stopShimmer();
        binding.shimmerLayout.visibility = View.GONE;
    }

}