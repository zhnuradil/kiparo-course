package com.kiparo.pizzaapp.core.ui

fun String.isValidEmail(): Boolean {
    return matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$".toRegex())
}

// Alphanumeric string of minimum 6 items
fun String.isValidPassword(): Boolean {
    return isNotEmpty() &&
            matches("^[a-zA-Z0-9]{6,}\$".toRegex())
}