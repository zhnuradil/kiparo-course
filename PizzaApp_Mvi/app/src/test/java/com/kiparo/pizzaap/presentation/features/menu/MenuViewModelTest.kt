package com.kiparo.pizzaap.presentation.features.menu

import com.kiparo.pizzaap.utils.MainDispatcherRule
import com.kiparo.pizzaap.utils.stubs.MenuItemStub
import com.kiparo.pizzaap.utils.stubs.MenuSectionStub
import com.kiparo.pizzaap.utils.stubs.PromotionStub
import com.kiparo.pizzaapp.domain.usecases.cart.AddToCartUseCase
import com.kiparo.pizzaapp.domain.usecases.menu.GetMenuItemsUseCase
import com.kiparo.pizzaapp.domain.usecases.menu.GetMenuSectionsUseCase
import com.kiparo.pizzaapp.domain.usecases.promoution.GetPromotionsUseCase
import com.kiparo.pizzaapp.presentation.features.menu.MenuUiState
import com.kiparo.pizzaapp.presentation.features.menu.MenuViewModel
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

private const val SELECTED_SECTION_TITLE = "Selected Test Section";
private const val NOT_SELECTED_SECTION_TITLE = "NOT Selected Test Section";
@OptIn(ExperimentalCoroutinesApi::class)
class MenuViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: MenuViewModel
    private lateinit var getMenuSectionsUseCaseMock: GetMenuSectionsUseCase
    private lateinit var getMenuItemsUseCaseMock: GetMenuItemsUseCase
    private lateinit var getPromotionsUseCaseMock: GetPromotionsUseCase
    private lateinit var addToCartUseCaseMock: AddToCartUseCase

    @Before
    fun setup() {
        getMenuSectionsUseCaseMock = mockk(relaxed = true)
        getMenuItemsUseCaseMock = mockk(relaxed = true)
        getPromotionsUseCaseMock = mockk(relaxed = true)
        addToCartUseCaseMock = mockk(relaxed = true)
    }

    @After
    fun teardown() {
        clearAllMocks()
    }

    @Test
    fun uiState_whenInitialized_thenShowLoading() = runTest {
        viewModel = MenuViewModel(
            getMenuSectionsUseCase = getMenuSectionsUseCaseMock,
            getMenuItemsUseCase = getMenuItemsUseCaseMock,
            getPromotionsUseCase = getPromotionsUseCaseMock,
            addToCartUseCase = addToCartUseCaseMock
        )
        assertEquals(MenuUiState.Loading, viewModel.uiState.value)
    }

    @Test
    fun uiState_whenSectionSelected_thenShowSelectedSection() = runTest {
        coEvery { getMenuSectionsUseCaseMock.execute() } returns sectionsList()
        coEvery { getMenuItemsUseCaseMock.execute(SELECTED_SECTION_TITLE) } returns itemsList()
        coEvery { getPromotionsUseCaseMock.execute() } returns promotion()

        viewModel = MenuViewModel(
            getMenuSectionsUseCase = getMenuSectionsUseCaseMock,
            getMenuItemsUseCase = getMenuItemsUseCaseMock,
            getPromotionsUseCase = getPromotionsUseCaseMock,
            addToCartUseCase = addToCartUseCaseMock
        )

        viewModel.onSectionSelected(SELECTED_SECTION_TITLE)
        val collectState = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }


        val resultState = MenuUiState.Success(
            sections = sectionsList(),
            selectedSectionIndex = 0,
            products = itemsList(),
            promotion = promotion()

        )
        coVerify { getMenuItemsUseCaseMock.execute(SELECTED_SECTION_TITLE) }
        assertEquals(resultState, viewModel.uiState.value)

        collectState.cancel()
    }

    @Test
    fun whenAddToCart_thenAddToCartUseCaseExecuted(){
        viewModel = MenuViewModel(
            getMenuSectionsUseCase = getMenuSectionsUseCaseMock,
            getMenuItemsUseCase = getMenuItemsUseCaseMock,
            getPromotionsUseCase = getPromotionsUseCaseMock,
            addToCartUseCase = addToCartUseCaseMock
        )
        val addedItem = MenuItemStub.generate(34, SELECTED_SECTION_TITLE)
        viewModel.addToCart(addedItem)

        coVerify(exactly = 1){
            addToCartUseCaseMock.execute(addedItem)
        }
    }
}

private fun itemsList() = persistentListOf(
    MenuItemStub.generate(1, SELECTED_SECTION_TITLE),
    MenuItemStub.generate(2, SELECTED_SECTION_TITLE),
    MenuItemStub.generate(3, SELECTED_SECTION_TITLE),
    MenuItemStub.generate(4, SELECTED_SECTION_TITLE),
    MenuItemStub.generate(5, NOT_SELECTED_SECTION_TITLE),
    MenuItemStub.generate(6, NOT_SELECTED_SECTION_TITLE),
    MenuItemStub.generate(7, NOT_SELECTED_SECTION_TITLE),
    MenuItemStub.generate(8, NOT_SELECTED_SECTION_TITLE),
    MenuItemStub.generate(9, NOT_SELECTED_SECTION_TITLE)
)

private fun sectionsList() = persistentListOf(
    MenuSectionStub.generate(SELECTED_SECTION_TITLE),
    MenuSectionStub.generate(NOT_SELECTED_SECTION_TITLE)
)

private const val TEST_PROMOTION_TITLE = "test promotion"
private fun promotion() = PromotionStub.generate(TEST_PROMOTION_TITLE)
