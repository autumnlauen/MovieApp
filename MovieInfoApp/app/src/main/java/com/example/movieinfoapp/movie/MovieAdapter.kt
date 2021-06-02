package com.example.movieinfoapp.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movieinfoapp.databinding.ListItemBinding
import com.example.movieinfoapp.network.IMAGE_BASE_URL
import com.example.movieinfoapp.actor.actorNetwork.MovieProfileForActorCredits

typealias MovieClickListener = (MovieProfileForActorCredits) -> Unit

class MovieAdapter(private val clickListener: MovieClickListener) :
    ListAdapter<MovieProfileForActorCredits,
            RecyclerView.ViewHolder>(MovieDiffCallback()) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).render(
            clickListener,
            getItem(position) as MovieProfileForActorCredits
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class ViewHolder constructor(private val views: ListItemBinding) :
        RecyclerView.ViewHolder(views.root) {

        fun render(clickListener: MovieClickListener, item: MovieProfileForActorCredits) {
            views.listItemName.text = item.titleString
            views.listItemPicture.load("$IMAGE_BASE_URL${item.poster_path}") {
                error(android.R.drawable.ic_menu_camera)
                placeholder(android.R.drawable.progress_indeterminate_horizontal)
            }
            views.root.setOnClickListener {
                clickListener(item)
            }
        }
    }
}

class MovieDiffCallback : DiffUtil.ItemCallback<MovieProfileForActorCredits>() {
    override fun areItemsTheSame(
        oldItem: MovieProfileForActorCredits,
        newItem: MovieProfileForActorCredits
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: MovieProfileForActorCredits,
        newItem: MovieProfileForActorCredits
    ): Boolean {
        return oldItem == newItem
    }
}
