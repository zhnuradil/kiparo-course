package com.kiparo.wildcatalog.presentation.catalog.model

data class AdvertisementItem(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val isFavorite: Boolean
) : CatalogItem {
    override fun id() = id

    override fun content(another: CatalogItem): Boolean {
        return another is AdvertisementItem
                && title == another.title
                && description == another.description
                && imageUrl == another.imageUrl
                && isFavorite == another.isFavorite
    }

    override fun getPayload(another: CatalogItem): CatalogItem.PayloadChange {
        if (another is AdvertisementItem) {
            if (title != another.title) {
                return PayloadChange.TitlePayload(another.title)
            } else if (description != another.description) {
                return PayloadChange.DescriptionPayload(another.description)
            } else if (imageUrl != another.imageUrl) {
                return PayloadChange.ImagePayload(another.imageUrl)
            } else if (isFavorite != another.isFavorite) {
                return PayloadChange.FavoritePayload(another.isFavorite)
            }
        }
        return CatalogItem.PayloadChange.None
    }

    sealed interface PayloadChange : CatalogItem.PayloadChange {
        data class TitlePayload(val title: String) : PayloadChange
        data class DescriptionPayload(val description: String) : PayloadChange
        data class ImagePayload(val imageUrl: String) : PayloadChange
        data class FavoritePayload(val isFavorite: Boolean) : PayloadChange
    }
}
