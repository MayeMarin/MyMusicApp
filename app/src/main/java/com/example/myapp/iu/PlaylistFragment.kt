package com.example.myapp.iu

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapp.R
import com.example.myapp.core.Resource
import com.example.myapp.data.model.PlaylistItem
import com.example.myapp.databinding.FragmentPlaylistBinding
import com.example.myapp.iu.adapters.PlayListAdapter
import com.example.myapp.presentation.PlayListViewModel
import com.example.myapp.repository.Repository


class PlaylistFragment : Fragment(R.layout.fragment_playlist), PlayListAdapter.OnPlayListClickListener {

    private lateinit var binding: FragmentPlaylistBinding
    private val adapter by lazy { PlayListAdapter(this) }

    private val viewModel by viewModels<PlayListViewModel> {
        PlayListViewModel.PlayListViewModelFactory(
            Repository
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPlaylistBinding.bind(view)
        binding.rvPlayLists.adapter = adapter
        binding.rvPlayLists.layoutManager = GridLayoutManager(context, SPAN_COUNT)

        viewModel.fetchUpPlayList().observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    Log.d("LiveData.", "Loading.")
                }
                is Resource.Success -> {
                    Log.d("LiveData.", "${it.data}")
                    // adapter
                    adapter.playList = it.data.tracks.items
                    binding.rvPlayLists.adapter = adapter

                }
                is Resource.Failure -> {
                    Log.d("Error", it.error)
                }
            }
        }
    }

    companion object {
        const val SPAN_COUNT = 1
    }

    override fun onPlayListClick(playlist: PlaylistItem) {
        playlist.track.previewUrl?.let { previewUrl ->
            val action = PlaylistFragmentDirections.actionPlaylistFragmentToPlayerActivity(
                playlist.track.name,
                playlist.track.album.images[0].url,
                previewUrl
            )
            findNavController().navigate(action)
        }
    }
}
