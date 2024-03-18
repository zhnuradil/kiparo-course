package com.kiparo.deliveryapp.presentation.utils

import com.kiparo.deliveryapp.domain.models.Customer

fun Customer.getSender(): String {
    if (name.isNotEmpty()) {
        return name
    }
    return email
}