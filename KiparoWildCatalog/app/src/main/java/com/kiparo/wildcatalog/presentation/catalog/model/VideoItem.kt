package com.kiparo.wildcatalog.presentation.catalog.model

data class VideoItem(
    val id: String,
    val title: String,
    val description: String,
    val videoUri: String
) : CatalogItem {
    override fun id() = id
    override fun content(another: CatalogItem): Boolean {
        return another is VideoItem
                && title == another.title
                && description == another.description
                && videoUri == another.videoUri
    }

}
