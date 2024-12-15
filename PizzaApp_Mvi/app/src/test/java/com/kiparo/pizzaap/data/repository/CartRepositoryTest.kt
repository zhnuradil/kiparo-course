package com.kiparo.pizzaap.data.repository

import com.kiparo.pizzaap.data.test_tools.mockMenuItem
import com.kiparo.pizzaap.data.test_tools.mockMenuItemEntity
import com.kiparo.pizzaapp.data.mappers.toDomain
import com.kiparo.pizzaapp.data.mappers.toStorage
import com.kiparo.pizzaapp.data.repository.CartRepositoryImpl
import com.kiparo.pizzaapp.data.storage.api.CartStorage
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CartRepositoryTest {
    private val storage = mockk<CartStorage>(relaxed = true)
    private val dispatcher = UnconfinedTestDispatcher()
    private val repository = CartRepositoryImpl(cartStorage = storage, dispatcher = dispatcher)

    private val testMenuItem = mockMenuItem(id = "1")
    private val testMenuItemsEntity = listOf(
        mockMenuItemEntity(id = "10"),
        mockMenuItemEntity(id = "20"),
        mockMenuItemEntity(id = "30")
    )

    private val flowCartItemsEntity = flowOf(testMenuItemsEntity)

    @Test
    fun `add cart menu`() = runTest(dispatcher) {
        val expected = testMenuItem.toStorage()

        repository.add(item = testMenuItem)

        verify(exactly = 1) { storage.add(expected) }
    }

    @Test
    fun `remove cart menu`() = runTest(dispatcher) {
        val testId = "20"

        repository.remove(id = testId)

        verify(exactly = 1) { storage.remove(testId) }
    }

    @Test
    fun `observe cart menu`() = runTest(dispatcher) {
        coEvery { storage.observe() } returns flowCartItemsEntity

        val expected = testMenuItemsEntity.map { it.toDomain() }

        val actual = repository.observe().first()

        assertEquals(expected, actual)
    }

    @Test
    fun `observe cart size`() = runTest(dispatcher) {
        val testValue = 10
        coEvery { storage.observeSize() } returns flowOf(testValue)

        val actual = repository.observeSize().first()

        assertEquals(testValue, actual)
    }
}