/*
 * Copyright (c) 2023. Kiparo academy
 */

package com.kiparo.deliveryapp.domain.repository

import com.kiparo.deliveryapp.domain.models.ShipmentGroup

interface ShipmentRepository {
    suspend fun getGroupedByHighlight(): ShipmentGroup
}
