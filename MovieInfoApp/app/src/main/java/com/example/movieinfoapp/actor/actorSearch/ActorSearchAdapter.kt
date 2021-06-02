package com.example.movieinfoapp.actor.actorSearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movieinfoapp.actor.Actor
import com.example.movieinfoapp.databinding.ListItemBinding
import com.example.movieinfoapp.network.IMAGE_BASE_URL

typealias SearchedActorClickListener = (Int) -> Unit

class ActorSearchAdapter(private val clickListener: SearchedActorClickListener) :
        ListAdapter<Actor,RecyclerView.ViewHolder>(ActorDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).render(
            clickListener,
            getItem(position) as Actor
        )
    }

}

class ViewHolder constructor(private val views: ListItemBinding) :
    RecyclerView.ViewHolder(views.root) {

        fun render(clickListener: SearchedActorClickListener, item: Actor) {
            views.listItemName.text = item.name
            views.listItemPicture.load("$IMAGE_BASE_URL${item.profile_path}") {
                error(android.R.drawable.ic_menu_camera)
                placeholder(android.R.drawable.progress_indeterminate_horizontal)
            }
            views.root.setOnClickListener { clickListener(item.id) }
        }
    }

class ActorDiffCallback : DiffUtil.ItemCallback<Actor>() {
    override fun areItemsTheSame(oldItem: Actor, newItem: Actor): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Actor, newItem: Actor): Boolean {
        return oldItem == newItem
    }

}