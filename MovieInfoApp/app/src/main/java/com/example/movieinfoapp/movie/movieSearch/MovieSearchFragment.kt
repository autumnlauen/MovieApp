package com.example.movieinfoapp.movie.movieSearch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movieinfoapp.R
import com.example.movieinfoapp.databinding.FragmentMovieSearchBinding
import com.example.movieinfoapp.viewBindings

class MovieSearchFragment :
    Fragment(R.layout.fragment_movie_search) {

    private val movieSeachAdapter = MovieSearchAdapter {
        findNavController().navigate(
            MovieSearchFragmentDirections.actionMovieSearchFragmentToMovieFragment(it)
        )
    }

    private val views by viewBindings(FragmentMovieSearchBinding::bind)

    private val viewModel: MovieSearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        views.searchButton.setOnClickListener {
            viewModel.onSearchClicked(views.searchView.query.toString())
        }

        viewModel.viewState.observe(viewLifecycleOwner) {
            movieSeachAdapter.submitList(it.movies)
        }

        views.searchedMovieList.adapter = movieSeachAdapter
    }
}
