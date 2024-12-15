package com.kiparo.pizzaap.presentation.features.cart

import com.kiparo.pizzaap.utils.MainDispatcherRule
import com.kiparo.pizzaap.utils.stubs.MenuItemStub
import com.kiparo.pizzaapp.domain.usecases.cart.ObserveCartUseCase
import com.kiparo.pizzaapp.domain.usecases.cart.RemoveFromCartUseCase
import com.kiparo.pizzaapp.presentation.features.cart.CartUiState
import com.kiparo.pizzaapp.presentation.features.cart.CartViewModel
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coJustAwait
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

private const val TEST_SECTION_TITLE = "Test Title"
private const val ITEM_ID_TO_REMOVE = "9"
class CartViewModelTest {
    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: CartViewModel
    private lateinit var removeFromCartUseCaseMock: RemoveFromCartUseCase
    private lateinit var getCartUseCaseMock: ObserveCartUseCase

    @Before
    fun setup() {
        removeFromCartUseCaseMock = mockk(relaxed = true)
        getCartUseCaseMock = mockk(relaxed = true)
    }

    @After
    fun teardown() {
        clearAllMocks()
    }

    @Test
    fun uiState_whenInitialized_thenEmptyListInUiState() = runTest {
        viewModel = CartViewModel(
            getCartUseCase = getCartUseCaseMock,
            removeFromCartUseCase = removeFromCartUseCaseMock
        )
        assertEquals(CartUiState(cart = persistentListOf()),
            viewModel.uiState.value)
    }

    @Test
    fun uiState_whenHasItems_thenItemsListInUiState() = runTest {
        coEvery {
            getCartUseCaseMock.execute()
        } returns flowOf(itemsList())

        viewModel = CartViewModel(
            getCartUseCase = getCartUseCaseMock,
            removeFromCartUseCase = removeFromCartUseCaseMock
        )
        assertEquals(CartUiState(cart = itemsList().toPersistentList()),
            viewModel.uiState.value)
        coVerify {
            getCartUseCaseMock.execute()
        }
    }

    @Test
    fun whenRemoveItem_thenRemoveUseCaseIsExecuted() = runTest {
        coJustAwait {
            removeFromCartUseCaseMock.execute(ITEM_ID_TO_REMOVE)
        }

        viewModel = CartViewModel(
            getCartUseCase = getCartUseCaseMock,
            removeFromCartUseCase = removeFromCartUseCaseMock
        )

        viewModel.removeFromCart(ITEM_ID_TO_REMOVE)

        coVerify {
            removeFromCartUseCaseMock.execute(ITEM_ID_TO_REMOVE)
        }
    }
}

private fun itemsList() = listOf(
    MenuItemStub.generate(1, TEST_SECTION_TITLE),
    MenuItemStub.generate(2, TEST_SECTION_TITLE),
    MenuItemStub.generate(9, TEST_SECTION_TITLE)
)