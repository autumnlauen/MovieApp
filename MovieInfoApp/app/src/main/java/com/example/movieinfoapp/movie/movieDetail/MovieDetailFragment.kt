package com.example.movieinfoapp.movie.movieDetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.movieinfoapp.actor.ActorAdapter
import com.example.movieinfoapp.R
import com.example.movieinfoapp.databinding.FragmentMovieDetailBinding
import com.example.movieinfoapp.network.IMAGE_BASE_URL
import com.example.movieinfoapp.viewBindings

class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private val actorAdapter = ActorAdapter {
        findNavController().navigate(
            MovieDetailFragmentDirections.actionMovieFragmentToActorFragment(
                it.id
            )
        )
    }

    private val views by viewBindings(FragmentMovieDetailBinding::bind)

    private val viewModel: MovieDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.setMovieId(MovieDetailFragmentArgs.fromBundle(requireArguments()).movieId)

        viewModel.viewState.observe(viewLifecycleOwner) {
            it?.let(::render)
        }

        views.actorsRecycler.adapter = actorAdapter
    }

    private fun render(viewState: MovieDetailViewState) {
        actorAdapter.submitList(viewState.actors)
        views.movieTitle.text = viewState.movie.title
        views.year.text = viewState.movie.releaseDate
        views.runtime.text = viewState.movie.runtimeString
        views.moviePicture.load("$IMAGE_BASE_URL${viewState.movie.poster_path}") {
            error(android.R.drawable.ic_menu_camera)
            placeholder(android.R.drawable.progress_indeterminate_horizontal)
        }
    }
}
