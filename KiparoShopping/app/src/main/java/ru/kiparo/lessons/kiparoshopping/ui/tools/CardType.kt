package ru.kiparo.lessons.kiparoshopping.ui.tools

import java.util.regex.Pattern

enum class CardType {
    VISA, MASTERCARD, AMERICAN_EXPRESS, MIR, MAESTRO, UNKNOWN
}

fun getCardTypeFromNumber(number: String): CardType {
    val visaRegex = Pattern.compile("^4[0-9]*\$")
    val mirRegex = Pattern.compile("^220(0|4)[0-9]*\$")
    val mastercardRegex =
        Pattern.compile("^(5[1-5]|222[1-9]|22[3-9]|2[3-6]|27[01]|2720)[0-9]*\$")
    val americanExpressRegex = Pattern.compile("^3[47][0-9]*\$")
    val maestroRegex = Pattern.compile("^(5[06789]|6)[0-9]*\$")

    return when {
        visaRegex.matcher(number).matches() ->
            CardType.VISA
        mastercardRegex.matcher(number).matches() ->
            CardType.MASTERCARD
        americanExpressRegex.matcher(number)
            .matches() ->
            CardType.AMERICAN_EXPRESS
        mirRegex.matcher(number).matches() -> CardType.MIR
        maestroRegex.matcher(number).matches() -> CardType.MAESTRO
        else ->
            CardType.UNKNOWN

    }
}