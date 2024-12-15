package com.kiparo.pizzaapp.app

import android.app.Application
import com.kiparo.pizzaapp.BuildConfig
import com.kiparo.pizzaapp.app.di.DiProvider
import com.kiparo.pizzaapp.app.di.GlobalDi
import com.kiparo.pizzaapp.app.di.GlobalDiImpl
import com.kiparo.pizzaapp.core.navigation.TopDestinationsCollection
import com.kiparo.pizzaapp.data.network.api.KiparoPizzaApiNetwork
import com.kiparo.pizzaapp.data.repository.CartRepositoryImpl
import com.kiparo.pizzaapp.data.repository.MenuRepositoryImpl
import com.kiparo.pizzaapp.data.repository.PromotionRepositoryImpl
import com.kiparo.pizzaapp.data.storage.api.CartStorage
import com.kiparo.pizzaapp.data.storage.api.MenuStorage
import com.kiparo.pizzaapp.data.storage.api.PromotionStorage
import com.kiparo.pizzaapp.data.storage.memory.CartMemoryStorage
import com.kiparo.pizzaapp.data.storage.memory.MenuMemoryStorage
import com.kiparo.pizzaapp.data.storage.memory.PromotionMemoryStorage
import com.kiparo.pizzaapp.domain.repository.CartRepository
import com.kiparo.pizzaapp.domain.repository.MenuRepository
import com.kiparo.pizzaapp.domain.repository.PromotionRepository
import com.kiparo.pizzaapp.domain.usecases.cart.AddToCartUseCase
import com.kiparo.pizzaapp.domain.usecases.cart.ObserveCartSizeUseCase
import com.kiparo.pizzaapp.domain.usecases.cart.ObserveCartUseCase
import com.kiparo.pizzaapp.domain.usecases.menu.GetMenuItemsUseCase
import com.kiparo.pizzaapp.domain.usecases.menu.GetMenuSectionsUseCase
import com.kiparo.pizzaapp.domain.usecases.promoution.GetPromotionsUseCase
import com.kiparo.pizzaapp.domain.usecases.menu.GetSingleMenuItemUseCase
import com.kiparo.pizzaapp.domain.usecases.cart.RemoveFromCartUseCase
import com.kiparo.pizzaapp.presentation.features.cart.navigation.CartTopLevelDestination
import com.kiparo.pizzaapp.presentation.features.menu.navigation.MenuTopLevelDestination
import com.kiparo.pizzaapp.presentation.features.profile.navigation.ProfileTopLevelDestination
import com.kiparo.pizzaapp.presentation.features.promo.navigation.PromoTopLevelDestination
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.collections.immutable.persistentListOf

class PizzaApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        DiProvider.di = initDi()
    }

    private fun initDi(): GlobalDi {
        val di = GlobalDiImpl()

        // network api
        val api = provideKiparoPizzaApi(
            BuildConfig.API_URL,
            provideMoshi()
        )
        di.add(KiparoPizzaApiNetwork::class, api)

        // storage
        di.add(MenuStorage::class, MenuMemoryStorage())
        di.add(CartStorage::class, CartMemoryStorage())
        di.add(PromotionStorage::class, PromotionMemoryStorage())

        // repositories
        di.add(
            MenuRepository::class, MenuRepositoryImpl(
                menuStorage = di.get(MenuStorage::class),
                pizzaApi = di.get(KiparoPizzaApiNetwork::class)
            )
        )
        di.add(
            CartRepository::class, CartRepositoryImpl(
                cartStorage = di.get(CartStorage::class)
            )
        )
        di.add(
            PromotionRepository::class, PromotionRepositoryImpl(
                promoStorage = di.get(PromotionStorage::class)
            )
        )

        // use cases
        di.add(
            AddToCartUseCase::class, AddToCartUseCase(
                repository = di.get(CartRepository::class)
            )
        )
        di.add(
            ObserveCartSizeUseCase::class, ObserveCartSizeUseCase(
                repository = di.get(CartRepository::class)
            )
        )
        di.add(
            ObserveCartUseCase::class, ObserveCartUseCase(
                repository = di.get(CartRepository::class)
            )
        )
        di.add(
            RemoveFromCartUseCase::class, RemoveFromCartUseCase(
                repository = di.get(CartRepository::class)
            )
        )
        di.add(
            GetMenuItemsUseCase::class, GetMenuItemsUseCase(
                repository = di.get(MenuRepository::class)
            )
        )
        di.add(
            GetMenuSectionsUseCase::class, GetMenuSectionsUseCase(
                repository = di.get(MenuRepository::class)
            )
        )
        di.add(
            GetSingleMenuItemUseCase::class,
            GetSingleMenuItemUseCase(repository = di.get(MenuRepository::class))
        )
        di.add(
            GetPromotionsUseCase::class, GetPromotionsUseCase(
                repository = di.get(
                    PromotionRepository::class
                )
            )
        )

        // navigation
        di.add(
            TopDestinationsCollection::class, TopDestinationsCollection(
                items = persistentListOf(
                    MenuTopLevelDestination(),
                    PromoTopLevelDestination(),
                    CartTopLevelDestination(),
                    ProfileTopLevelDestination()
                )
            )
        )

        return di
    }
}


private fun provideMoshi(): Moshi {
    return Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
}

@Suppress("SameParameterValue")
private fun provideKiparoPizzaApi(apiUrl: String, moshi: Moshi): KiparoPizzaApiNetwork {
    return KiparoPizzaApiNetwork(
        apiUrl = apiUrl,
        moshi = moshi
    )
}
