/*
 * Copyright (c) 2023. Kiparo.ru
 */

package com.kiparo.newsapp.presentation.utils

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

// TODO Implement
private val formatterDate = DateTimeFormatter.ofPattern("IMPLEMENT")

// TODO Implement
private val formatterTime = DateTimeFormatter.ofPattern("IMPLEMENT")

fun formatDate(date: ZonedDateTime): String = date.format(formatterDate)

fun formatTime(date: ZonedDateTime): String = date.format(formatterTime)