package com.atom.searchcoffe

import com.atom.searchcoffe.domain.dto.CartItem
import com.atom.searchcoffe.domain.dto.Coffee
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {

    private val gson = Gson()

    fun convertOrderListToJson(orderList: List<CartItem>): String {
        return gson.toJson(orderList)
    }

    fun parseOrderJson(json: String): List<CartItem> {
        val type = object : TypeToken<List<CartItem>>() {}.type
        return gson.fromJson(json, type)
    }
}