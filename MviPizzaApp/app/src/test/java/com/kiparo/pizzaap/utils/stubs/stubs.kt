package com.kiparo.pizzaap.utils.stubs

import com.kiparo.pizzaapp.domain.models.MenuItem
import com.kiparo.pizzaapp.domain.models.MenuSection
import com.kiparo.pizzaapp.domain.models.Promotion

class MenuSectionStub {
    companion object {
        fun generate(title: String) =
            MenuSection(
                section = title, image = "test_image_url_for_$title"
            )
    }
}

class MenuItemStub {
    companion object {
        private const val PRICE_MULTIPLIER = 3
        fun generate(id: Int, sectionTitle: String) =
            MenuItem(
                section = MenuSectionStub.generate(sectionTitle),
                id = "test-menu-item-$id",
                title = "test-title-$id",
                description = "test-description-$id",
                image = "test-image-$id",
                price = "${id * PRICE_MULTIPLIER}"
            )
    }
}

class PromotionStub {
    companion object {
        fun generate(title: String) =
            Promotion(
                title = title,
                offerPromo = "offer promo for $title",
                offerPromoPrice = "300",
                promoCode = "promo code for $title"
            )
    }
}

