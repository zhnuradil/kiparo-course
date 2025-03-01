package com.kiparo.wildcatalog.presentation.catalog.model

data class ProductItem(
    val id: String,
    val title: String,
    val price: String,
    val imageUrl: String
) : CatalogItem {
    override fun id() = id
    override fun content(another: CatalogItem): Boolean {
        return another is ProductItem
                && title == another.title
                && price == another.price
                && imageUrl == another.imageUrl
    }
}