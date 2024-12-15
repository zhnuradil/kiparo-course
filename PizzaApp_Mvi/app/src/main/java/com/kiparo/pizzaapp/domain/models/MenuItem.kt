package com.kiparo.pizzaapp.domain.models

data class MenuItem(
    val section: MenuSection,
    val id: String,
    val title: String,
    val description: String,
    val image: String,
    val price: String
) {
    companion object
}