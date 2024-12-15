package com.kiparo.pizzaapp.domain.repository

import com.kiparo.pizzaapp.domain.models.Promotion

interface PromotionRepository {
    suspend fun get(): Promotion
}