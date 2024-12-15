/*
 * Copyright (c) 2023. Kiparo.ru
 */

package com.kiparo.pizzaapp.data.mappers

import com.kiparo.pizzaapp.data.network.model.MenuItemNetwork
import com.kiparo.pizzaapp.data.storage.model.MenuItemEntity
import com.kiparo.pizzaapp.data.storage.model.MenuSectionEntity
import com.kiparo.pizzaapp.domain.models.MenuItem

internal fun MenuItemNetwork.toStorage(sections: List<MenuSectionEntity>): MenuItemEntity {
    val section = sections.first { section ->
        section.section == this.section
    }

    return MenuItemEntity(
        section = section,
        id = id,
        title = title,
        description = description,
        image = image,
        price = price

    )
}

internal fun MenuItem.toStorage(): MenuItemEntity {

    return MenuItemEntity(
        section = section.toStorage(),
        id = id,
        title = title,
        description = description,
        image = image,
        price = price
    )
}

internal fun MenuItemEntity.toDomain(): MenuItem {
    return MenuItem(
        section = section.toDomain(),
        id = id,
        title = title,
        description = description,
        image = image,
        price = price

    )
}