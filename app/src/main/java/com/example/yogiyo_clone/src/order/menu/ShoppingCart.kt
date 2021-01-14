package com.example.yogiyo_clone.src.order.menu

import java.io.Serializable

data class ShoppingCart(
    val storeIdx: Int,
    val menus: MutableList<ShoppingItem>,
    val delivery_fee:Int,
    val discount_price:Int,
    var totalPrice:Int
):Serializable

data class ShoppingItem(
    var count: Int=0,
    var menuIdx: Int,
    var menuName: String,
    var options: MutableList<ShoppingOptionItem>?,
    var menuPrice:Int=0
):Serializable

data class ShoppingOptionItem(
    val optionIdx:Int,
    val optionName:String,
    val optionPrice:Int,
):Serializable