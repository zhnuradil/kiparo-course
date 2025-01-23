package com.kiparo.wildcatalog.presentation.catalog.model

data class AudioItem(
    val id: String,
    val title: String
) : CatalogItem {

    override fun id(): String = id
}