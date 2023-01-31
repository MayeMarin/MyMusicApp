package com.example.myapp.iu.adapters.concat

import AlbumsAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.core.BaseConcatHolder
import com.example.myapp.databinding.AlbumsRowBinding

class AlbumsConcatAdapter(private val albumsAdapter: AlbumsAdapter): RecyclerView.Adapter<BaseConcatHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val itemBinding = AlbumsRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConcatViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when (holder) {
            is ConcatViewHolder -> holder.bind(albumsAdapter)
            else -> throw IllegalArgumentException("No viewholder to show this data, did you forgot to add it to the onBindViewHolder?")
        }
    }

    override fun getItemCount(): Int = 1

    private inner class ConcatViewHolder(val binding: AlbumsRowBinding) : BaseConcatHolder<AlbumsAdapter>(binding.root) {
        override fun bind(adapter: AlbumsAdapter) {
            binding.recyclerViewAlbums.adapter = adapter
        }
    }
}