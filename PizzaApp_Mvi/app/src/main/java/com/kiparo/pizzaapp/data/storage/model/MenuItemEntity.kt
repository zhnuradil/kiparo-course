package com.kiparo.pizzaapp.data.storage.model

data class MenuItemEntity(
    val section: MenuSectionEntity,
    val id: String,
    val title: String,
    val description: String,
    val image: String,
    val price: String,
)
