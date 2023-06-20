package com.example.moviepopcorn.ui.movie

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.moviepopcorn.R
import com.example.moviepopcorn.databinding.FragmentMovieBinding
import com.example.moviepopcorn.data.model.Movie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
//class movie fragment akan meng-implement terhadap class fragment
//dimasukkan argumen di Fragment berupa layout fragment movie
class MovieFragment : Fragment(R.layout.fragment_movie), MovieAdapter.OnItemClickListener{

    private val viewModel by viewModels<MovieViewModel>()
    private var _binding : FragmentMovieBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//      data binding untuk mengikat data ke bagian UI
        _binding = FragmentMovieBinding.bind(view)

//      adapter untuk movie
        val adapter = MovieAdapter(this)

//      recycler view
        binding.apply {
            rvMovie.setHasFixedSize(true)
            rvMovie.adapter = adapter.withLoadStateHeaderAndFooter(
//              blok kode untuk menghandle load state
                header = MovieLoadStateAdapter {adapter.retry()},
                footer = MovieLoadStateAdapter {adapter.retry()}
            )
//            button retry jika data gagal dimuat
            btnRetry.setOnClickListener {
                adapter.retry()
            }
        }

        //observer untuk mengamati LiveData
        viewModel.movies.observe(viewLifecycleOwner){
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

//      blok kode untuk menangani jika data gagal dimuat maka akan muncul button retry
        adapter.addLoadStateListener { loadState ->
            binding.apply {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                rvMovie.isVisible = loadState.source.refresh is LoadState.NotLoading
                btnRetry.isVisible =loadState.source.refresh is LoadState.Error
                tvFailed.isVisible = loadState.source.refresh is LoadState.Error

                //kondisi jika data tidak ditemukan saat mencari film
                if (loadState.source.refresh is LoadState.NotLoading &&
                        loadState.append.endOfPaginationReached &&
                        adapter.itemCount < 1){
                    rvMovie.isVisible = false
                    tvNotFound.isVisible = true
                } else {
                    tvNotFound.isVisible = false
                }
            }
        }
        setHasOptionsMenu(true)
    }

//    perpindahan fragment dari item movie ke detail fragment
    override fun onItemClick(movie: Movie) {
        val action = MovieFragmentDirections.actionNavMovieToNavDetails(movie)
        findNavController().navigate(action)
    }

//  fungsi untuk membuat search menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_search, menu)

//      variabel search item berdasarkan layout action search
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query!=null){
                    binding.rvMovie.scrollToPosition(0)
//                  mencari film berdasarkan query yang dimasukkan user
                    viewModel.searchMovies(query)
                    searchView.clearFocus()
                }
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }
}