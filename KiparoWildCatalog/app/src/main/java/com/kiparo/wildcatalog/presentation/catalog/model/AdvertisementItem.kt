package com.kiparo.wildcatalog.presentation.catalog.model

data class AdvertisementItem(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val isFavorite: Boolean
) :
    CatalogItem {
    override fun id() = id
}
