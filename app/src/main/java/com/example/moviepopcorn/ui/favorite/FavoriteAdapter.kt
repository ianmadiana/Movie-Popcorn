package com.example.moviepopcorn.ui.favorite

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.moviepopcorn.R
import com.example.moviepopcorn.data.local.FavoriteMovie
import com.example.moviepopcorn.databinding.ItemMovieBinding

//class FavoriteAdapter akan meng-inherit atau mewarisi terhadap class RecyclerView
class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

//    variabel list dari favorite movie
    private lateinit var list : List<FavoriteMovie>

    private var onItemClickCallback: OnItemClickCallback? = null

//
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    fun setMovieList(list: List<FavoriteMovie>){
        this.list = list
        notifyDataSetChanged()
    }

//  view holder
    inner class FavoriteViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(favoriteMovie: FavoriteMovie) {
            with(binding) {

//              blok kode untuk layout menggunakan Glide digunakan sebagai image loader
                Glide.with(itemView)
                    .load("${favoriteMovie.baseUrl}${favoriteMovie.poster_path}")
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error24)
                    .into(ivPoster)
                //blok kode untuk menampilkan data berupa judul, deskripsi dan rating
                tvTitle.text = favoriteMovie.original_title
                tvDesc.text = favoriteMovie.overview
                tvRating.text = favoriteMovie.vote_average
                binding.root.setOnClickListener { onItemClickCallback?.onItemClick(favoriteMovie) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        Log.e("adapter", "Masuk bind view holder")
        holder.bind(list[position])
    }

    interface OnItemClickCallback {
        fun onItemClick(favoriteMovie: FavoriteMovie)
    }
}