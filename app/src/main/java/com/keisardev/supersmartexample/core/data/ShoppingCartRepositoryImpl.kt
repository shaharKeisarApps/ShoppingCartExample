package com.keisardev.supersmartexample.core.data

import com.keisardev.supersmartexample.core.domain.abstractions.ShoppingCartRepository
import com.keisardev.supersmartexample.feature_list.domain.ShoppingCartItem
import kotlinx.coroutines.flow.*


class ShoppingCartRepositoryImpl : ShoppingCartRepository {
    private val _shoppingCartSharedFlow =
        MutableSharedFlow<List<ShoppingCartItem>>()
    val shoppingCartSharedFlow = _shoppingCartSharedFlow.asSharedFlow()

    var itemDetailsList = mutableListOf<ShoppingCartItem>()
   /* override suspend fun getShoppingCartList(): Flow<List<ShoppingCartItem>> = channelFlow {
        send(itemDetailsList)
    }


    override suspend fun addItem(shoppingCartItem: ShoppingCartItem) {
        itemDetailsList.add(shoppingCartItem)
    }

    override suspend fun removeItem(index: Int) {
        itemDetailsList.removeAt(index)
    }

    override suspend fun getItem(index: Int): Flow<ShoppingCartItem> = channelFlow {
        send(itemDetailsList[index])
    }

    override suspend fun updateItem( shoppingCartItem: ShoppingCartItem) {
        itemDetailsList[shoppingCartItem.index-1] = shoppingCartItem
    }*/

    override suspend fun getShoppingCartList(): Flow<List<ShoppingCartItem>> = channelFlow {
        shoppingCartSharedFlow.collectLatest {
            send(it)
        }
    }


    override suspend fun addItem(shoppingCartItem: ShoppingCartItem) {
        itemDetailsList.add(shoppingCartItem)
        _shoppingCartSharedFlow.emit(itemDetailsList)
    }

    override suspend fun removeItem(index: Int) {
        itemDetailsList.removeAt(index)
        _shoppingCartSharedFlow.emit(itemDetailsList)
    }

    override suspend fun getItem(index: Int): Flow<ShoppingCartItem> = channelFlow {
        send(shoppingCartSharedFlow.first()[index])
    }

    override suspend fun updateItem( shoppingCartItem: ShoppingCartItem) {
        itemDetailsList[shoppingCartItem.index-1] = shoppingCartItem
        _shoppingCartSharedFlow.emit(itemDetailsList)
    }
}