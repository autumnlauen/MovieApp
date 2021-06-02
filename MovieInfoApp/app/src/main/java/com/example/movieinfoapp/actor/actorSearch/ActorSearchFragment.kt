package com.example.movieinfoapp.actor.actorSearch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movieinfoapp.R
import com.example.movieinfoapp.databinding.FragmentActorSearchBinding
import com.example.movieinfoapp.viewBindings

class ActorSearchFragment : Fragment(R.layout.fragment_actor_search) {

    private val actorSearchAdapter = ActorSearchAdapter {
        findNavController().navigate(
            ActorSearchFragmentDirections.actionActorSearchFragmentToActorFragment(it)
        )
    }

    private val views by viewBindings(FragmentActorSearchBinding::bind)

    private val viewModel: ActorSearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        views.actorSearchButton.setOnClickListener {
            viewModel.onSearchClicked(views.actorSearchView.query.toString())
        }

        viewModel.viewState.observe(viewLifecycleOwner) {
            actorSearchAdapter.submitList(it.actors)
        }

        views.searchedActorList.adapter = actorSearchAdapter
    }
}
