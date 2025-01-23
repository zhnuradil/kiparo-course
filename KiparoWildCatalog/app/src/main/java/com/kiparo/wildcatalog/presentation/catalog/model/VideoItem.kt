package com.kiparo.wildcatalog.presentation.catalog.model

data class VideoItem(
    val id : String,
    val title: String,
    val description: String,
    val videoUri: String
 ) : CatalogItem {
    override fun id() = id
}
