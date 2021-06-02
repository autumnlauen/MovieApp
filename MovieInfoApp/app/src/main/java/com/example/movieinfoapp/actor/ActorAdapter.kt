package com.example.movieinfoapp.actor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movieinfoapp.databinding.ListItemBinding
import com.example.movieinfoapp.movie.movieNetwork.ActorProfileForMovieCredits
import com.example.movieinfoapp.network.IMAGE_BASE_URL


typealias ActorClickListener = (ActorProfileForMovieCredits) -> Unit

class ActorAdapter(private val clickListener: ActorClickListener) :
    ListAdapter<ActorProfileForMovieCredits,
            RecyclerView.ViewHolder>(ActorDiffCallback()) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).render(
            clickListener,
            getItem(position) as ActorProfileForMovieCredits //diff on lin 57, no sleep night equivalent here
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

        fun render(clickListener: ActorClickListener, item: ActorProfileForMovieCredits) {
            views.listItemName.text = item.nameString
            views.listItemPicture.load("$IMAGE_BASE_URL${item.profile_path}") {
                error(android.R.drawable.ic_menu_camera)
                placeholder(android.R.drawable.progress_indeterminate_horizontal)
            }
            views.root.setOnClickListener {
                clickListener(item)
            }
        }
    }
}

class ActorDiffCallback : DiffUtil.ItemCallback<ActorProfileForMovieCredits>() {
    override fun areItemsTheSame(
        oldItem: ActorProfileForMovieCredits,
        newItem: ActorProfileForMovieCredits
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: ActorProfileForMovieCredits,
        newItem: ActorProfileForMovieCredits
    ): Boolean {
        return oldItem == newItem
    }
}
