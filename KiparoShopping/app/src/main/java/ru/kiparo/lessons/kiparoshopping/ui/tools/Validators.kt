package ru.kiparo.lessons.kiparoshopping.ui.tools

fun String.isValidEmail() : Boolean {
    val emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
    return matches(emailRegex)
}