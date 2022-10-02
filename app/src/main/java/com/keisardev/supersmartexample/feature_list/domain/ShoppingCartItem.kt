package com.keisardev.supersmartexample.feature_list.domain

data class ShoppingCartItem(
    val index: Int = 0,
    val amount: String = "",
    val name: String = "",
    val price: String= "",
    val barcode: String = "",
    val description: String = "",

)
