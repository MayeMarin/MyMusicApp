package com.example.myapp.iu.adapters.concat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.core.BaseConcatHolder
import com.example.myapp.databinding.TracksRowBinding
import com.example.myapp.iu.adapters.TracksAdapter

class TracksConcatAdapter(private val tracksAdapter: TracksAdapter): RecyclerView.Adapter<BaseConcatHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val itemBinding = TracksRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConcatViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when (holder) {
            is ConcatViewHolder -> holder.bind(tracksAdapter)
            else -> throw IllegalArgumentException("No viewholder to show this data, did you forgot to add it to the onBindViewHolder?")
        }
    }

    override fun getItemCount(): Int = 1

    private inner class ConcatViewHolder(val binding: TracksRowBinding) : BaseConcatHolder<TracksAdapter>(binding.root) {
        override fun bind(adapter: TracksAdapter) {
            binding.recyclerViewTracks.adapter = adapter
        }
    }
}