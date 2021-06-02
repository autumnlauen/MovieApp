package com.example.movieinfoapp.choose

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.movieinfoapp.R
import com.example.movieinfoapp.databinding.FragmentChooseBinding
import com.example.movieinfoapp.viewBindings

class ChooseFragment : Fragment(R.layout.fragment_choose) {

    private val views by viewBindings(FragmentChooseBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        views.chooseMovieButton.setOnClickListener (
            Navigation.createNavigateOnClickListener(R.id.action_chooseFragment_to_movieSearchFragment)
        )
        views.chooseActorButton.setOnClickListener (
            Navigation.createNavigateOnClickListener(R.id.action_chooseFragment_to_actorSearchFragment)
        )
    }
}