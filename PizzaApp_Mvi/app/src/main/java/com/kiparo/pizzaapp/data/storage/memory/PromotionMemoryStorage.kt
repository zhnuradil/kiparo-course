/*
 * Copyright (c) 2023. Kiparo.ru
 */

package com.kiparo.pizzaapp.data.storage.memory

import com.kiparo.pizzaapp.data.storage.api.PromotionStorage
import com.kiparo.pizzaapp.data.storage.model.PromotionEntity

// This is a mocking solution for the learning purposes only
// to cache the items for the time of the app working
class PromotionMemoryStorage : PromotionStorage {
    private val promotions = mutableSetOf(
        PromotionEntity(
            title = "Today's Offer",
            offerPromo = "Free Box of {{Fries!}}",
            offerPromoPrice = "on all orders above {{$150}}",
            promoCode = "kiparo2023"
        )
    )

    override fun get(): PromotionEntity =
        promotions.first()
}