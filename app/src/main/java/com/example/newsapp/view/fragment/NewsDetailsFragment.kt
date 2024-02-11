package com.example.newsapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentNewsDetailsBinding
import com.example.newsapp.viewmodel.NewsDetailsViewModel

class NewsDetailsFragment : Fragment() {

    private lateinit var binding: FragmentNewsDetailsBinding
    private lateinit var viewModel: NewsDetailsViewModel

    private val args: NewsDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_news_details, container, false
        )

        viewModel = ViewModelProvider(this)[NewsDetailsViewModel::class.java]

        val news = args.news
        binding.textNewsHeadline.text = news.title
        binding.textNewsDescription.text = news.description

        binding.btBack.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

}