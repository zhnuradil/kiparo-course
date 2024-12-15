/*
 * Copyright (c) 2023. Kiparo.ru
 */

package com.kiparo.pizzaapp.data.mappers

import com.kiparo.pizzaapp.data.network.model.SectionItemNetwork
import com.kiparo.pizzaapp.data.storage.model.MenuSectionEntity
import com.kiparo.pizzaapp.domain.models.MenuSection

internal fun SectionItemNetwork.toStorage() =
    MenuSectionEntity(
        section = section,
        image = image
    )

internal fun MenuSection.toStorage() =
    MenuSectionEntity(
        section = section,
        image = image
    )


internal fun MenuSectionEntity.toDomain() =
    MenuSection(
        section = section,
        image = image
    )