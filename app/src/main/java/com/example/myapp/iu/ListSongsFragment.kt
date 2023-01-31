package com.example.myapp.iu

import AlbumsAdapter
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.myapp.R
import com.example.myapp.core.Resource
import com.example.myapp.databinding.FragmentListSongsBinding
import com.example.myapp.iu.adapters.PlayListAdapter
import com.example.myapp.iu.adapters.TracksAdapter
import com.example.myapp.iu.adapters.concat.AlbumsConcatAdapter
import com.example.myapp.presentation.AlbumViewModel
import com.example.myapp.repository.Repository
import com.squareup.picasso.Picasso


class ListSongsFragment : Fragment(R.layout.fragment_list_songs){

    private val args by navArgs<ListSongsFragmentArgs>()
    private lateinit var binding: FragmentListSongsBinding

    private val viewModel by activityViewModels<AlbumViewModel> {
        AlbumViewModel.AlbumViewModelFactory(
            Repository
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListSongsBinding.bind(view)


        val album = viewModel.albumsResponse.first { it.id == args.id }


        Picasso.get()
            .load(album.images.firstOrNull()?.url.orEmpty())
            .error(R.mipmap.ic_launcher).into(binding.imageAlbumPlaySongs)
        binding.PlaySongsNameArtist.text = album.artists.firstOrNull()?.name
        binding.PlaySongsNameAlbum.text = album.name
        binding.albumType.text = album.albumType

        binding.listSongs.text = album.tracks.items.joinToString(separator = "···") { it.name }

    }

}