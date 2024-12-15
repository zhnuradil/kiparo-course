/*
 * Copyright (c) 2023. Kiparo academy
 */

package com.kiparo.pizzaapp.data.repository

import com.kiparo.pizzaapp.data.exceptions.MenuFetchException
import com.kiparo.pizzaapp.data.exceptions.MenuItemFetchException
import com.kiparo.pizzaapp.data.exceptions.SectionsFetchException
import com.kiparo.pizzaapp.data.mappers.toDomain
import com.kiparo.pizzaapp.data.mappers.toStorage
import com.kiparo.pizzaapp.data.network.api.KiparoPizzaApi
import com.kiparo.pizzaapp.data.storage.api.MenuStorage
import com.kiparo.pizzaapp.domain.models.MenuItem
import com.kiparo.pizzaapp.domain.models.MenuSection
import com.kiparo.pizzaapp.domain.repository.MenuRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.withContext

class MenuRepositoryImpl(
    private val menuStorage: MenuStorage,
    private val pizzaApi: KiparoPizzaApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : MenuRepository {
    override suspend fun get(filterBySection: String?): List<MenuItem> =
        withContext(dispatcher) {
            try {
                if (menuStorage.getAll().isEmpty()) {
                    val sections = pizzaApi.getSections().map { it.toStorage() }
                    val menuItems = pizzaApi.getMenu().map { it.toStorage(sections) }
                    withContext(NonCancellable){
                        menuStorage.addSections(sections)
                        menuStorage.add(menuItems)
                    }
                }

                if (filterBySection != null) {
                    menuStorage.getByFilter(filterBySection).map { it.toDomain() }
                } else {
                    menuStorage.getAll().map { it.toDomain() }
                }
            } catch (cause: Throwable) {
                if(cause is CancellationException) throw cause
                throw MenuFetchException(cause)
            }
        }

    override suspend fun getById(id: String): MenuItem =
        withContext(dispatcher) {
            try {
                menuStorage.getById(id).toDomain()
            } catch (cause: Throwable) {
                if(cause is CancellationException) throw cause
                throw MenuItemFetchException(cause)
            }
        }

    override suspend fun getSections(): List<MenuSection> =
        withContext(dispatcher) {
            try {
                if (menuStorage.getSections().isEmpty()) {
                    val sections = pizzaApi.getSections().map { it.toStorage() }
                    withContext(NonCancellable){
                        menuStorage.addSections(sections = sections)
                    }
                }
                menuStorage.getSections().map { it.toDomain() }
            } catch (cause: Throwable) {
                if(cause is CancellationException) throw cause
                throw SectionsFetchException(cause)
            }
        }
}


