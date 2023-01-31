package com.example.myapp.iu.adapters.concat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.core.BaseConcatHolder
import com.example.myapp.databinding.ArtistsRowBinding
import com.example.myapp.iu.adapters.ArtistsAdapter

class ArtistsConcatAdapter(private val artistsAdapter: ArtistsAdapter): RecyclerView.Adapter<BaseConcatHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val itemBinding = ArtistsRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConcatViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when (holder) {
            is ConcatViewHolder -> holder.bind(artistsAdapter)
            else -> throw IllegalArgumentException("No viewholder to show this data, did you forgot to add it to the onBindViewHolder?")
        }
    }

    override fun getItemCount(): Int = 1

    private inner class ConcatViewHolder(val binding: ArtistsRowBinding) : BaseConcatHolder<ArtistsAdapter>(binding.root) {
        override fun bind(adapter: ArtistsAdapter) {
            binding.recyclerViewArtists.adapter = adapter
        }
    }
}