package com.example.myapp.iu.adapters
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.R
import com.example.myapp.core.BaseViewHolder
import com.example.myapp.data.model.PlaylistItem
import com.example.myapp.databinding.ItemArtistBinding
import com.example.myapp.databinding.ItemPlaylistBinding

class PlayListAdapter(
    private val itemClickListener: OnPlayListClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    var playList: List<PlaylistItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    interface OnPlayListClickListener {
        fun onPlayListClick(playlist: PlaylistItem)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = ItemPlaylistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = PlayListViewHolder(itemBinding, parent.context)
        itemBinding.root.setOnClickListener {
            val position = holder.bindingAdapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                ?: return@setOnClickListener
            itemClickListener.onPlayListClick(playList[position])
        }
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is PlayListViewHolder -> holder.bind(playList[position])
        }
    }
    override fun getItemCount(): Int = playList.size

    class PlayListViewHolder(
        val binding: ItemPlaylistBinding,
        val context: Context) : BaseViewHolder<PlaylistItem>(binding.root) {

        override fun bind(playlist: PlaylistItem) {
            binding.nameSongs.text = playlist.track.name
            binding.nameArtist.text = playlist.track.artists[0].name
        }
    }
}



