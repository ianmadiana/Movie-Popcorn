package com.example.moviepopcorn.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.moviepopcorn.data.model.Movie
import com.example.moviepopcorn.databinding.ItemMovieBinding
import com.example.moviepopcorn.R

//class MovieAdapter akan mewarisi (inherit) dari PagingDataAdapter
class MovieAdapter(private val listener : OnItemClickListener) : PagingDataAdapter<Movie, MovieAdapter.MovieViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
//        variabel untuk menampung data terkini
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }
//view binding dari item movie
//class MovieViewHolder akan mengimplementasikan RecyclerView
    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

//    constructor
        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION){
                    val item = getItem(position)
                    if (item!=null){
                        listener.onItemClick(item)
                    }
                }
            }
        }

        fun bind(movie: Movie) {
            with(binding) {
//                blok kode untuk layout menggunakan Glide digunakan sebagai image loader
                Glide.with(itemView)
                    .load("${movie.baseUrl}${movie.poster_path}")
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error_34_512)
                    .into(ivPoster)

//                blok kode untuk menampilkan data berupa judul, deskripsi dan rating
                tvTitle.text = movie.original_title
                tvDesc.text = movie.overview
                tvRating.text = movie.vote_average
            }
        }
    }

//    interface untuk menuju ke halaman detail film
    interface OnItemClickListener{
        fun onItemClick(movie: Movie)
    }

    companion object {
//        blok kode untuk mengkomparasi data karena data movie yang dinamis
        private val COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem == newItem
        }
    }

}