package com.example.myapp.iu

import AlbumsAdapter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.example.myapp.R
import com.example.myapp.core.Resource
import com.example.myapp.data.model.Albums
import com.example.myapp.data.model.Artist
import com.example.myapp.data.model.Tracks
import com.example.myapp.databinding.FragmentAlbumsBinding
import com.example.myapp.iu.adapters.ArtistsAdapter
import com.example.myapp.iu.adapters.TracksAdapter
import com.example.myapp.iu.adapters.concat.AlbumsConcatAdapter
import com.example.myapp.iu.adapters.concat.ArtistsConcatAdapter
import com.example.myapp.iu.adapters.concat.TracksConcatAdapter
import com.example.myapp.presentation.AlbumViewModel
import com.example.myapp.repository.Repository

class AlbumsFragment : Fragment(R.layout.fragment_albums), AlbumsAdapter.OnAlbumClickListener,
    ArtistsAdapter.OnArtistClickListener, TracksAdapter.OnTracksClickListener {
    private lateinit var concatAdapter: ConcatAdapter
    private lateinit var binding: FragmentAlbumsBinding

    private val viewModel by activityViewModels<AlbumViewModel> {
        AlbumViewModel.AlbumViewModelFactory(
            Repository
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAlbumsBinding.bind(view)
        concatAdapter = ConcatAdapter()
        //binding.rvAlbum.layoutManager = GridLayoutManager(context, SPAN_COUNT)


        viewModel.fetchUpAlbums().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    Log.d("LiveData", "Loading...")
                }
                is Resource.Success -> {
                    Log.d("LiveData", "${result.data}")
                    val list = if (result.data != null) listOf(result.data) else emptyList()
                    concatAdapter.addAdapter(AlbumsConcatAdapter(AlbumsAdapter(list[0].albums,this@AlbumsFragment)))
                    binding.recyclerView.adapter = concatAdapter

                }

                is Resource.Failure -> {
                    Log.d("Error", result.error)
                }
            }
        })
        viewModel.fetchUpArtists().observe(viewLifecycleOwner, Observer { results ->
            when (results) {
                is Resource.Loading -> {
                    Log.d("Live-Data", "Loading")
                }
                is Resource.Success -> {
                    Log.d("Live-Data", "${results.data}")
                    val list_ = if (results.data != null) listOf(results.data) else emptyList()
                    concatAdapter.addAdapter(ArtistsConcatAdapter(ArtistsAdapter(list_[0].artists,this@AlbumsFragment)))
                    binding.recyclerView.adapter = concatAdapter
                }
                is Resource.Failure -> {
                    Log.d("Error", results.error)
                }
            }
        })
        viewModel.fetchTracks().observe(viewLifecycleOwner, Observer { it ->
            when (it) {
                is Resource.Loading -> {
                    Log.d("Data", "Loading")
                }
                is Resource.Success -> {
                    Log.d("Data", "${it.data}")
                    val list1 = if (it.data != null) listOf(it.data) else emptyList()
                    concatAdapter.addAdapter(TracksConcatAdapter(TracksAdapter(list1[0].tracks,this@AlbumsFragment)))
                    binding.recyclerView.adapter = concatAdapter
                }
                is Resource.Failure -> {
                    Log.d("Error", it.error)
                }
            }
        })
    }

    override fun onAlbumClick(albums: Albums) {
        val action = AlbumsFragmentDirections
            .actionAlbumFragmentToListSongsFragment(
                albums.id
            )
        findNavController().navigate(action)

    }

    companion object {
        const val SPAN_COUNT = 2
    }

    override fun onArtistClick(artist: Artist) {
        val action1 = AlbumsFragmentDirections.actionAlbumFragmentToArtistFragment(
            artist.id
        )
        findNavController().navigate(action1)
    }
    override fun onTracksClick(tracks: Tracks) {
        tracks.previewUrl?.let { previewUrl ->
            val action = AlbumsFragmentDirections.actionAlbumFragmentToPlayerActivity(
                tracks.name,
                tracks.album.images[0].url,
                previewUrl
            )
            findNavController().navigate(action)
        }

    }

}

