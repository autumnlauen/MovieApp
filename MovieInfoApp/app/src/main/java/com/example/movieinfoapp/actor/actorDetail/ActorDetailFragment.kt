package com.example.movieinfoapp.actor.actorDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.movieinfoapp.movie.MovieAdapter
import com.example.movieinfoapp.R
import com.example.movieinfoapp.databinding.FragmentActorDetailBinding
import com.example.movieinfoapp.network.IMAGE_BASE_URL
import com.example.movieinfoapp.viewBindings

class ActorDetailFragment : Fragment(R.layout.fragment_actor_detail) {

    private val movieAdapter = MovieAdapter {
        findNavController().navigate(ActorDetailFragmentDirections.actionActorFragmentToMovieFragment(it.id))
    }

    private val views by viewBindings(FragmentActorDetailBinding::bind)

    private val viewModel: ActorDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.setActorId(ActorDetailFragmentArgs.fromBundle(requireArguments()).actorId)

        viewModel.viewState.observe(viewLifecycleOwner) {
            it?.let(::render)
        }

        views.movieRecycler.adapter = movieAdapter
    }

    private fun render(viewState: ActorDetailViewState) {
        movieAdapter.submitList(viewState.movies)
        views.actorName.text = viewState.actor.name
        views.birthday.text = viewState.actor.age
        views.birthPlace.text = viewState.actor.placeOfBirth
        views.actorPicture.load("$IMAGE_BASE_URL${viewState.actor.profile_path}") {
            error(android.R.drawable.ic_menu_camera)
            placeholder(android.R.drawable.progress_indeterminate_horizontal)
        }
    }
}
