package com.example.movieinfoapp.movie.movieSearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movieinfoapp.databinding.ListItemBinding
import com.example.movieinfoapp.movie.Movie
import com.example.movieinfoapp.network.IMAGE_BASE_URL

typealias SearchedMovieClickListener = (Int) -> Unit

class MovieSearchAdapter(private val clickListener: SearchedMovieClickListener) :
    ListAdapter<Movie, RecyclerView.ViewHolder>(MovieDiffCallback()) {
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
            getItem(position) as Movie
        )
    }
}

class ViewHolder constructor(private val views: ListItemBinding) :
    RecyclerView.ViewHolder(views.root) {

    fun render(clickListener: SearchedMovieClickListener, item: Movie) {
        views.listItemName.text = item.title
        views.listItemPicture.load("$IMAGE_BASE_URL${item.poster_path}") {
            error(android.R.drawable.ic_menu_camera)
            placeholder(android.R.drawable.progress_indeterminate_horizontal)
        }
        views.root.setOnClickListener { clickListener(item.id) }
    }
}

class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}