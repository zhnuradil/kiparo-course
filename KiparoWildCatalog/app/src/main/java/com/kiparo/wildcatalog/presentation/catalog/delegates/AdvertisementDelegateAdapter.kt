package com.kiparo.wildcatalog.presentation.catalog.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.kiparo.wildcatalog.R
import com.kiparo.wildcatalog.databinding.ItemCatalogAdvBinding
import com.kiparo.wildcatalog.presentation.catalog.model.AdvertisementItem
import com.kiparo.wildcatalog.presentation.catalog.model.CatalogItem

class AdvertisementDelegateAdapter(
    private val onFavoritesToggle: (AdvertisementItem) -> Unit
) : CatalogItemDelegateAdapter<AdvertisementItem, AdvertisementDelegateAdapter.ViewHolder> {

    override fun createViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(
            binding = ItemCatalogAdvBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onFavoritesToggle = onFavoritesToggle
        )
    }

    override fun bindViewHolder(item: AdvertisementItem, viewHolder: ViewHolder) {
        viewHolder.bind(item)
    }

    override fun bindViewHolder(
        item: AdvertisementItem,
        viewHolder: ViewHolder,
        payloads: List<CatalogItem.PayloadChange>
    ) {
        when (val payload = payloads.lastOrNull()) {
            is AdvertisementItem.PayloadChange.TitlePayload -> {
                viewHolder.bindTitle(payload.title)
            }

            is AdvertisementItem.PayloadChange.DescriptionPayload -> {
                viewHolder.bindDescription(payload.description)
            }

            is AdvertisementItem.PayloadChange.FavoritePayload -> {
                viewHolder.bindFavorite(payload.isFavorite)
            }

            is AdvertisementItem.PayloadChange.ImagePayload -> {
                viewHolder.bindImage(payload.imageUrl)
            }

            CatalogItem.PayloadChange.None -> viewHolder.bind(item)

            null -> viewHolder.bind(item)
        }
    }

    override val itemClass = AdvertisementItem::class.java

    class ViewHolder(
        private val binding: ItemCatalogAdvBinding,
        private val onFavoritesToggle: (AdvertisementItem) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        private val context = itemView.context

        private val circularProgress = CircularProgressDrawable(context)
        private val placeHolder = AppCompatResources.getDrawable(
            context,
            R.drawable.ic_adv_placeholder
        )

        fun bind(advertisement: AdvertisementItem) {
            bindTitle(advertisement.title)
            bindDescription(advertisement.description)
            bindFavorite(advertisement.isFavorite)
            bindImage(advertisement.imageUrl)
            binding.btnToggleFavorites.setOnClickListener { onFavoritesToggle(advertisement) }
        }

        fun bindTitle(title: String) {
            binding.txtTitle.text = title
        }

        fun bindDescription(description: String) {
            binding.txtDescription.text = description
        }

        fun bindFavorite(favorite: Boolean) {
            val image = if (favorite) R.drawable.ic_favorites_on else R.drawable.ic_favorites_off
            binding.btnToggleFavorites.setImageResource(image)
        }

        fun bindImage(imageUrl: String) {
            circularProgress.strokeWidth = 5f
            circularProgress.centerRadius = 30f
            circularProgress.start()

            Glide
                .with(context)
                .load(imageUrl)
                .fitCenter()
                .placeholder(circularProgress)
                .error(placeHolder)
                .into(binding.imgAdvImage)
        }
    }
}