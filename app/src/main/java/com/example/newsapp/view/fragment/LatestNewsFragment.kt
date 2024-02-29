package com.example.newsapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.data.News
import com.example.newsapp.data.adapter.NewsListAdapter
import com.example.newsapp.databinding.FragmentLatestNewsBinding
import com.example.newsapp.viewmodel.LatestNewsViewModel
import com.google.android.material.snackbar.Snackbar

class LatestNewsFragment : Fragment() {

    private lateinit var binding: FragmentLatestNewsBinding
    private lateinit var viewModel: LatestNewsViewModel
//    private lateinit var viewModelFactory: LatestNewsViewModelFactory

//    private val application: Application = requireActivity().application
//    private val database = NewsDatabase.getDatabase(application).newsDatabaseDao

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NewsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_latest_news, container, false
        )

//        viewModelFactory = LatestNewsViewModelFactory(database, application)

        viewModel = ViewModelProvider(this)[LatestNewsViewModel::class.java]
        binding.lifecycleOwner = this

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

        val swipeRefreshLayout = binding.swipeRefreshLayout

        viewModel.loadingFinished.observe(viewLifecycleOwner, Observer { loadingFinished ->
            if(loadingFinished == false)
                startAnimation()
            else
                stopAnimation()
        })

        viewModel.selectedCategory.observe(viewLifecycleOwner, Observer { selectedCategory ->
            when (selectedCategory) {
                0 -> {
                    resetUIColors()
                    binding.btLatestNews.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorPrimary))
                    viewModel.getNewsByCategory("top")
                }
                1 -> {
                    resetUIColors()
                    binding.btWorldNews.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorPrimary))
                    viewModel.getNewsByCategory("world")
                }
                2 -> {
                    resetUIColors()
                    binding.btPoliticsNews.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorPrimary))
                    viewModel.getNewsByCategory("politics")
                }
                3 -> {
                    resetUIColors()
                    binding.btBusinessNews.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorPrimary))
                    viewModel.getNewsByCategory("business")
                }
            }
        })

        swipeRefreshLayout.setOnRefreshListener {
            startAnimation()
            resetUIColors()
            binding.btLatestNews.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorPrimary))
            viewModel.getNews()
            swipeRefreshLayout.isRefreshing = false
        }

        binding.btLatestNews.setOnClickListener {
            viewModel.onClickCategory(0)
        }

        binding.btWorldNews.setOnClickListener {
            viewModel.onClickCategory(1)
        }

        binding.btPoliticsNews.setOnClickListener {
            viewModel.onClickCategory(2)
        }

        binding.btBusinessNews.setOnClickListener {
            viewModel.onClickCategory(3)
        }

    }

    private fun setupRecyclerView(newsList: List<News>) {
        recyclerView = binding.newsList

        adapter = NewsListAdapter(newsList) { news->
            val action = LatestNewsFragmentDirections.actionLatestNewsFragmentToNewsDetailsFragment(news = news)
            Navigation.findNavController(requireView()).navigate(action)
        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

    private fun startAnimation() {
        binding.shimmerLayout.startShimmer()
        binding.shimmerLayout.visibility = View.VISIBLE
    }

    private fun stopAnimation() {
        binding.shimmerLayout.stopShimmer()
        binding.shimmerLayout.visibility = View.GONE
    }

    private fun resetUIColors() {
        binding.btLatestNews.setTextColor(ContextCompat.getColor(requireContext(), R.color.textNewsSource))
        binding.btWorldNews.setTextColor(ContextCompat.getColor(requireContext(), R.color.textNewsSource))
        binding.btPoliticsNews.setTextColor(ContextCompat.getColor(requireContext(), R.color.textNewsSource))
        binding.btBusinessNews.setTextColor(ContextCompat.getColor(requireContext(), R.color.textNewsSource))
    }

}