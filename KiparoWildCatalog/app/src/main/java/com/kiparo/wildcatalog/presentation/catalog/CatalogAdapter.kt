package com.kiparo.wildcatalog.presentation.catalog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.media3.ui.PlayerView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.kiparo.wildcatalog.R
import com.kiparo.wildcatalog.presentation.catalog.model.AdvertisementItem
import com.kiparo.wildcatalog.presentation.catalog.model.CatalogItem
import com.kiparo.wildcatalog.presentation.catalog.model.ProductItem
import com.kiparo.wildcatalog.presentation.catalog.model.VideoItem

private const val PRODUCT_VIEW_TYPE = 0
private const val ADVERTISEMENT_VIEW_TYPE = 1
private const val VIDEO_VIEW_TYPE = 2

class CatalogAdapter(
    private val productRemoveListener: (product: ProductItem) -> Unit,
    private val generateNextVideoListener: (video: VideoItem) -> Unit,
    private val onFavoritesToggle: (AdvertisementItem) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: List<CatalogItem> = emptyList()
        set(newList) {
            val diffUtilCallback = DiffCallback(old = field, new = newList)
            val diffUtilResult = DiffUtil.calculateDiff(diffUtilCallback)
            field = newList
            diffUtilResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            PRODUCT_VIEW_TYPE -> {
                val view = LayoutInflater.from(parent.context).inflate(
                    R.layout.item_catalog_product,
                    parent,
                    false
                )
                ProductViewHolder(view, removeListener = productRemoveListener)
            }
            ADVERTISEMENT_VIEW_TYPE -> {
                val view = LayoutInflater.from(parent.context).inflate(
                    R.layout.item_catalog_adv,
                    parent,
                    false
                )
                AdViewHolder(view, onFavoritesToggle)
            }
            VIDEO_VIEW_TYPE -> {
                val view = LayoutInflater.from(parent.context).inflate(
                    R.layout.item_catalog_video,
                    parent,
                    false
                )
                VideoStreamViewHolder(
                    view,
                    generateNextVideoListener = generateNextVideoListener
                )
            }
            else -> throw IllegalArgumentException(
                "There is no view holder for list type: $viewType"
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            PRODUCT_VIEW_TYPE -> {
                val item = items[position] as ProductItem
                val viewHolder = holder as ProductViewHolder
                viewHolder.bind(item)
            }
            ADVERTISEMENT_VIEW_TYPE -> {
                val item = items[position] as AdvertisementItem
                val viewHolder = holder as AdViewHolder
                viewHolder.bind(item)
            }
            VIDEO_VIEW_TYPE -> {
                val item = items[position] as VideoItem
                val viewHolder = holder as VideoStreamViewHolder
                viewHolder.bind(item)
            }
            else -> throw IllegalArgumentException(
                "Unknown list type to bind data: ${holder.itemViewType}"
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is AdvertisementItem -> ADVERTISEMENT_VIEW_TYPE
            is ProductItem -> PRODUCT_VIEW_TYPE
            is VideoItem -> VIDEO_VIEW_TYPE
            else -> throw IllegalArgumentException(
                "Unknown list type"
            )
        }
    }

    override fun getItemCount(): Int = items.size


    internal class ProductViewHolder(
        itemView: View,
        private val removeListener: (product: ProductItem) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private val productImage: ImageView = itemView.findViewById(R.id.img_product)
        private val productTitle: TextView = itemView.findViewById(R.id.txt_product_name)
        private val productPrice: TextView =
            itemView.findViewById(R.id.txt_product_price)
        private val productRemoveBtn: ImageButton =
            itemView.findViewById(R.id.btn_product_remove_btn)

        fun bind(product: ProductItem) {
            productTitle.text = product.title
            productPrice.text = product.price

            val placeHolder = AppCompatResources.getDrawable(
                productImage.context,
                R.drawable.ic_product_placeholder
            )

            val circularProgress = CircularProgressDrawable(productImage.context)
            circularProgress.strokeWidth = 5f
            circularProgress.centerRadius = 30f
            circularProgress.start()

            Glide
                .with(productImage.context)
                .load(product.imageUrl)
                .fitCenter()
                .placeholder(circularProgress)
                .error(placeHolder)
                .into(productImage)

            productRemoveBtn.setOnClickListener { removeListener(product) }
        }
    }

    internal class AdViewHolder(
        itemView: View,
        private val onFavoritesToggle: (AdvertisementItem) -> Unit) : RecyclerView.ViewHolder(itemView) {

        private val adImage: ImageView = itemView.findViewById(R.id.img_adv_image)
        private val adTitle: TextView = itemView.findViewById(R.id.txt_title)
        private val adDescription: TextView =
            itemView.findViewById(R.id.txt_description)
        private val favoritesOn = R.drawable.ic_favorites_on
        private val favoritesOff = R.drawable.ic_favorites_off

        private val btnToggleFavorites: ImageButton = itemView.findViewById(R.id.btn_toggleFavorites)

        fun bind(advertisement: AdvertisementItem) {
            adDescription.tag
            adTitle.text = advertisement.title
            adDescription.text = advertisement.description
            btnToggleFavorites.setImageResource(if (advertisement.isFavorite) favoritesOn else favoritesOff)
            btnToggleFavorites.setOnClickListener {
                onFavoritesToggle(advertisement)
            }
            val placeHolder = AppCompatResources.getDrawable(
                adImage.context,
                R.drawable.ic_adv_placeholder
            )
            val circularProgress = CircularProgressDrawable(adImage.context)
            circularProgress.strokeWidth = 5f
            circularProgress.centerRadius = 30f
            circularProgress.start()

            Glide
                .with(adImage.context)
                .load(advertisement.imageUrl)
                .fitCenter()
                .placeholder(circularProgress)
                .error(placeHolder)
                .into(adImage)

        }
    }

    internal class VideoStreamViewHolder(
        itemView: View,
        private val generateNextVideoListener: (video: VideoItem) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private val videoView: PlayerView = itemView.findViewById(R.id.video_view)
        private val videoTitle: TextView = itemView.findViewById(R.id.txt_video_title)
        private val videoDescription: TextView = itemView.findViewById(R.id.txt_video_description)
        private val videoCloneButton: ImageButton = itemView.findViewById(R.id.btn_add_next)

        fun bind(video: VideoItem) {
            videoTitle.text = video.title
            videoDescription.text = video.description

            videoCloneButton.setOnClickListener {
                generateNextVideoListener(video)
            }
        }
    }

    inner class DiffCallback(
        private val old: List<CatalogItem>,
        private val new: List<CatalogItem>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            return old.size
        }

        override fun getNewListSize(): Int {
            return new.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return old[oldItemPosition].id() == new[newItemPosition].id()
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return old[oldItemPosition] == new[newItemPosition]
        }
    }
}
