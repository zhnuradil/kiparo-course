/*
 * Copyright (c) 2023. Kiparo.ru
 */

package com.kiparo.pizzaapp.data.storage.api

import com.kiparo.pizzaapp.data.storage.model.PromotionEntity

interface PromotionStorage {
    fun get(): PromotionEntity
}