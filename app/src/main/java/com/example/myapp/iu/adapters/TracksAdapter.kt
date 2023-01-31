package com.example.myapp.iu.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.R
import com.example.myapp.core.BaseViewHolder
import com.example.myapp.data.model.PlaylistItem
import com.example.myapp.data.model.Tracks
import com.example.myapp.databinding.ItemPlaylistBinding
import com.example.myapp.databinding.ItemSongBinding
import com.squareup.picasso.Picasso

class TracksAdapter(
    var tracks: List<Tracks> = emptyList(),
    private val itemClickListener: OnTracksClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnTracksClickListener {
        fun onTracksClick(tracks: Tracks)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = ItemSongBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = TracksViewHolder(itemBinding, parent.context)
        itemBinding.root.setOnClickListener {
            val position = holder.bindingAdapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                ?: return@setOnClickListener
            itemClickListener.onTracksClick(tracks[position])
        }
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is TracksViewHolder -> holder.bind(tracks[position])
        }
    }
    override fun getItemCount(): Int = tracks.size

    private inner class TracksViewHolder(
        val binding: ItemSongBinding,
        val context: Context
    ) : BaseViewHolder<Tracks>(binding.root) {
        override fun bind(tracks: Tracks) {
            Picasso.get()
                .load(tracks.album.images[0].url)
                .error(R.mipmap.ic_launcher).into(binding.imageAlbum)
            binding.nameSong.text = tracks.name
            binding.nameArtist.text = tracks.album.artists[0].name
        }
    }
}


