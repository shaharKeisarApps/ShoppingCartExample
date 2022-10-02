package com.keisardev.supersmartexample.core.domain.abstractions

import com.keisardev.supersmartexample.feature_list.domain.ShoppingCartItem
import kotlinx.coroutines.flow.Flow

interface ShoppingCartRepository {
    suspend fun  getShoppingCartList() : Flow<List<ShoppingCartItem>>
    suspend fun addItem(shoppingCartItem: ShoppingCartItem)
    suspend fun removeItem(index: Int)
    suspend fun getItem(index: Int): Flow<ShoppingCartItem>
    suspend fun updateItem(shoppingCartItem: ShoppingCartItem )
}