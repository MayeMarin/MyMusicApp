import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.R
import com.example.myapp.core.BaseViewHolder
import com.example.myapp.data.model.*
import com.example.myapp.databinding.ItemAlbumBinding
import com.example.myapp.iu.AlbumsFragment
import com.squareup.picasso.Picasso


class AlbumsAdapter(
    var albums: List<Albums> = emptyList(),
    private val itemClickListener: AlbumsFragment
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnAlbumClickListener {
        fun onAlbumClick(albums: Albums)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
            ItemAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = AlbumViewHolder(itemBinding, parent.context)
        itemBinding.root.setOnClickListener {
            val position =
                holder.bindingAdapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                    ?: return@setOnClickListener
            itemClickListener.onAlbumClick(albums[position])
        }
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is AlbumViewHolder -> holder.bind(albums[position])
        }
    }

    override fun getItemCount(): Int = albums.size

    private inner class AlbumViewHolder(
        val binding: ItemAlbumBinding,
        val context: Context
    ) : BaseViewHolder<Albums>(binding.root) {
        override fun bind(item: Albums) {
            binding.nameAlbum.text = item.name
            binding.artistName.text = item.artists[0].name
            Picasso.get()
                .load(item.images[0].url)
                .error(R.mipmap.ic_launcher).into(binding.imageAlbums)

        }

    }
}
