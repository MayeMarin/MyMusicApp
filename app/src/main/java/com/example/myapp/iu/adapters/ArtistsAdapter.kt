package com.example.myapp.iu.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.R
import com.example.myapp.core.BaseViewHolder
import com.example.myapp.data.model.Artist
import com.example.myapp.databinding.ItemArtistBinding
import com.squareup.picasso.Picasso

class ArtistsAdapter(var artist: List<Artist> = emptyList(),
    private val itemClickListener: OnArtistClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnArtistClickListener {
        fun onArtistClick(artist: Artist)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = ItemArtistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = ArtistsViewHolder(itemBinding, parent.context)
        itemBinding.root.setOnClickListener {
            val position = holder.bindingAdapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                ?: return@setOnClickListener
            itemClickListener.onArtistClick(artist[position])
        }
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is ArtistsViewHolder -> holder.bind(artist[position])
        }
    }

    override fun getItemCount(): Int = artist.size

    private inner class ArtistsViewHolder(
        val binding: ItemArtistBinding,
        val context: Context
    ) : BaseViewHolder<Artist>(binding.root) {

        override fun bind(item: Artist) {
            binding.artistName.text = item.name
            binding.genre.text = item.genres[0]
            Picasso.get()
                .load(item.images[0].url)
                .error(R.mipmap.ic_launcher).into(binding.imageArtist)
        }
    }
}
