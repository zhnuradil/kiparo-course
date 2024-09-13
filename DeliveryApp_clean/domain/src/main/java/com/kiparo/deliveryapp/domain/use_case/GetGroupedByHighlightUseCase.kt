package com.kiparo.deliveryapp.domain.use_case

import com.kiparo.deliveryapp.domain.models.ShipmentGroup
import com.kiparo.deliveryapp.domain.repository.ShipmentRepository

class GetGroupedByHighlightUseCase(
    private val shipmentRepository: ShipmentRepository
) {
    suspend fun execute(): ShipmentGroup {
        return shipmentRepository.getGroupedByHighlight()
    }
}