/*
 * Copyright (c) 2023. Kiparo.ru
 */

package com.kiparo.deliveryapp.presentation.utils

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

private val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy | HH:MM")

fun formatDateTime(date: ZonedDateTime): String = date.format(formatter)