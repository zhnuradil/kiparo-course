/*
 * Copyright (c) 2023. Kiparo.ru
 */

package com.kiparo.pizzaapp.domain.usecases.promoution

import com.kiparo.pizzaapp.domain.models.Promotion
import com.kiparo.pizzaapp.domain.repository.PromotionRepository
import com.kiparo.pizzaapp.domain.usecases.UseCaseWithoutParams

class GetPromotionsUseCase(private val repository: PromotionRepository) :
    UseCaseWithoutParams<Promotion> {
    override suspend fun execute(): Promotion = repository.get()
}