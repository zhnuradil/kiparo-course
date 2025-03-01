package com.kiparo.wildcatalog.presentation.catalog.model

interface CatalogItem {
    fun id(): Any
    fun content(another: CatalogItem): Boolean
    fun getPayload(another: CatalogItem): PayloadChange = PayloadChange.None

    sealed interface PayloadChange {
        data object None : PayloadChange
    }
}