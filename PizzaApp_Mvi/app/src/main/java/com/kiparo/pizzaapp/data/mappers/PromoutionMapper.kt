/*
 * Copyright (c) 2023. Kiparo.ru
 */

package com.kiparo.pizzaapp.data.mappers

import com.kiparo.pizzaapp.data.storage.model.PromotionEntity
import com.kiparo.pizzaapp.domain.models.Promotion

internal fun PromotionEntity.toDomain() =
    Promotion(
        title = title,
        offerPromo = offerPromo,
        offerPromoPrice = offerPromoPrice,
        promoCode = promoCode
    )