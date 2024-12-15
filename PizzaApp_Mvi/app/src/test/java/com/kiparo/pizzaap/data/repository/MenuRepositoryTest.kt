package com.kiparo.pizzaap.data.repository

import com.kiparo.pizzaap.data.test_tools.mockMenuItemEntity
import com.kiparo.pizzaap.data.test_tools.mockMenuItemNetwork
import com.kiparo.pizzaap.data.test_tools.mockMenuSectionNetwork
import com.kiparo.pizzaapp.data.exceptions.MenuFetchException
import com.kiparo.pizzaapp.data.mappers.toDomain
import com.kiparo.pizzaapp.data.mappers.toStorage
import com.kiparo.pizzaapp.data.network.api.KiparoPizzaApi
import com.kiparo.pizzaapp.data.repository.MenuRepositoryImpl
import com.kiparo.pizzaapp.data.storage.api.MenuStorage
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MenuRepositoryTest {
    private val storage = mockk<MenuStorage>(relaxed = true)
    private val pizzaApi = mockk<KiparoPizzaApi>()
    private val dispatcher = UnconfinedTestDispatcher()
    private val repository =
        MenuRepositoryImpl(menuStorage = storage, pizzaApi = pizzaApi, dispatcher = dispatcher)

    private val testMenuItemsNetwork = listOf(
        mockMenuItemNetwork(id = "2"),
        mockMenuItemNetwork(id = "3"),
        mockMenuItemNetwork(id = "4")
    )

    private val testMenuSectionNetwork = listOf(
        mockMenuSectionNetwork(section = "All"),
        mockMenuSectionNetwork(section = "Pizza"),
        mockMenuSectionNetwork(section = "IceCream")
    )

    private val testMenuItemsEntity = listOf(
        mockMenuItemEntity(id = "10"),
        mockMenuItemEntity(id = "20"),
        mockMenuItemEntity(id = "30")
    )

    @Test
    fun `check save to storage if storage is empty`() = runTest(dispatcher) {
        coEvery { storage.getAll() } returns emptyList()
        coEvery { pizzaApi.getMenu() } returns testMenuItemsNetwork
        coEvery { pizzaApi.getSections() } returns testMenuSectionNetwork

        val expectedSection = testMenuSectionNetwork.map { it.toStorage() }
        val expectedMenu = testMenuItemsNetwork.map { it.toStorage(expectedSection) }

        repository.get(filterBySection = null)

        verify(exactly = 1) { storage.add(expectedMenu) }
        verify(exactly = 1) { storage.addSections(expectedSection) }
    }

    @Test
    fun `check get by filter`() = runTest(dispatcher) {
        val testFilter = "testFilter"
        coEvery { storage.getAll() } returns testMenuItemsEntity
        coEvery { storage.getByFilter(testFilter) } returns testMenuItemsEntity
        val expected = testMenuItemsEntity.map { it.toDomain() }

        val actual = repository.get(filterBySection = testFilter)

        assertEquals(expected, actual)
    }

    @Test
    fun `check get with null filter`() = runTest(dispatcher) {
        val testFilter = null
        coEvery { storage.getAll() } returns testMenuItemsEntity
        val expected = testMenuItemsEntity.map { it.toDomain() }

        val actual = repository.get(filterBySection = testFilter)

        assertEquals(expected, actual)
    }

    @Test
    fun `check get with exception`() = runTest(dispatcher) {
        val testFilter = "testFilter"
        coEvery { storage.getAll() } returns testMenuItemsEntity
        coEvery { storage.getByFilter(testFilter) } throws Exception("Database exception")

        assertThrows(
            "",
            MenuFetchException::class.java
        ) { runTest(dispatcher) { repository.get(filterBySection = testFilter) } }
    }
}