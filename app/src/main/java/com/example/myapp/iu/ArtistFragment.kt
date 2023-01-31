package com.example.myapp.iu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapp.R
import com.example.myapp.databinding.FragmentArtistBinding
import com.example.myapp.presentation.AlbumViewModel
import com.example.myapp.repository.Repository
import com.squareup.picasso.Picasso

class ArtistFragment : Fragment(R.layout.fragment_artist) {

    private val args by navArgs<ArtistFragmentArgs>()
    private lateinit var binding : FragmentArtistBinding

    private val viewModel by activityViewModels<AlbumViewModel> {
        AlbumViewModel.AlbumViewModelFactory(
            Repository
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentArtistBinding.bind(view)

        val artist = viewModel.artistsResponse.first {it.id == args.id}

        Picasso.get()
            .load(artist.images.firstOrNull()?.url.orEmpty())
            .error(R.mipmap.ic_launcher).into(binding.imageLArtist)
        binding.nameArtists.text = artist.name
        binding.genreArtist.text = artist.genres.joinToString(separator = "··")




    }
}