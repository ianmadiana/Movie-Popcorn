package com.example.moviepopcorn.ui.favorite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.moviepopcorn.R
import com.example.moviepopcorn.data.local.FavoriteMovie
import com.example.moviepopcorn.data.model.Movie
import com.example.moviepopcorn.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
//class favorite fragment akan meng-implement terhadap class fragment
//dimasukkan argumen di Fragment berupa layout fragment favorite
class FavoriteFragment : Fragment(R.layout.fragment_favorite){
    private val viewModel by viewModels<FavoriteViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//      data binding untuk mengikat data ke bagian UI
        val binding = FragmentFavoriteBinding.bind(view)

        val adapter = FavoriteAdapter()

//      observer untuk mengamati LiveData
        viewModel.movies.observe(viewLifecycleOwner){
            adapter.setMovieList(it)
            binding.apply {
                rvMovie.setHasFixedSize(true)
                rvMovie.adapter = adapter
            }
        }

        adapter.setOnItemClickCallback(object : FavoriteAdapter.OnItemClickCallback{
            override fun onItemClick(favoriteMovie: FavoriteMovie) {
                val movie = Movie(
                    favoriteMovie.id_movie,
                    favoriteMovie.overview,
                    favoriteMovie.poster_path,
                    favoriteMovie.popularity,
                    favoriteMovie.vote_average,
                    favoriteMovie.original_title
                )
                val action = FavoriteFragmentDirections.actionNavFavoriteToNavDetails(movie)
                findNavController().navigate(action)
            }

        })
    }
}