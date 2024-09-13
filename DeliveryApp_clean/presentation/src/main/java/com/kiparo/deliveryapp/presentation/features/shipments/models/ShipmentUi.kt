/*
 * Copyright (c) 2023. Kiparo.ru
 */

package com.kiparo.deliveryapp.presentation.features.shipments.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class ShipmentUi(
    val number: String,
    @StringRes
    val status: Int,
    @DrawableRes
    val typeIconResId: Int,
    val sender: String,
    @StringRes
    val statusDescription: Int,
    val statusDateTime: String
)