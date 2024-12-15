package com.kiparo.pizzaapp.data.network.api

import com.kiparo.pizzaapp.data.network.model.MenuItemNetwork
import com.kiparo.pizzaapp.data.network.model.MenuNetworkResponse
import com.kiparo.pizzaapp.data.network.model.SectionItemNetwork
import com.kiparo.pizzaapp.data.network.model.SectionsNetworkResponse
import com.squareup.moshi.Moshi
import java.net.HttpURLConnection
import java.net.URL

private const val SECTIONS = "sections/"

class KiparoPizzaApiNetwork(
    private val apiUrl: String,
    private val moshi: Moshi,
) : KiparoPizzaApi {
    override fun getMenu(): List<MenuItemNetwork> {
        val url = URL(apiUrl)
        val con = url.openConnection() as HttpURLConnection
        val json = con.inputStream.bufferedReader().use { it.readText() }

        val jsonAdapter = moshi.adapter(MenuNetworkResponse::class.java)
        val response = jsonAdapter.fromJson(json) as MenuNetworkResponse

        return response.results
    }

    override fun getSections(): List<SectionItemNetwork> {
        val url = URL(apiUrl + SECTIONS)
        val con = url.openConnection() as HttpURLConnection
        val json = con.inputStream.bufferedReader().use { it.readText() }

        val jsonAdapter = moshi.adapter(SectionsNetworkResponse::class.java)
        val response = jsonAdapter.fromJson(json) as SectionsNetworkResponse

        return response.results
    }
}
