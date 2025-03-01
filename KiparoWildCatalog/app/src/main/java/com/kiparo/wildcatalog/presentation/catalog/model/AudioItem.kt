package com.kiparo.wildcatalog.presentation.catalog.model

data class AudioItem(
    val id: String,
    val title: String
) : CatalogItem {
    override fun id(): String = id
    override fun content(another: CatalogItem): Boolean {
        return another is AudioItem && title == another.title
    }
}