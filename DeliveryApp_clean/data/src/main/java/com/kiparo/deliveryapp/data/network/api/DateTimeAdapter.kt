package com.kiparo.deliveryapp.data.network.api

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class DateTimeAdapter {

    private val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME

    @FromJson
    fun toZonedDateTime(value: String): ZonedDateTime = formatter.parse(value, ZonedDateTime::from)

    @ToJson
    fun fromZonedDateTime(date: ZonedDateTime?): String? = date?.format(formatter)
}
