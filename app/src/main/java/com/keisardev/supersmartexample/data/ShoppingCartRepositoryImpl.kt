package com.keisardev.supersmartexample.data

import com.keisardev.supersmartexample.domain.abstractions.ShoppingCartRepository
import com.keisardev.supersmartexample.feature_list.domain.ShoppingCartItem
import kotlinx.coroutines.flow.*


class ShoppingCartRepositoryImpl : ShoppingCartRepository {
    private val _shoppingCartSharedFlow =
        MutableSharedFlow<List<ShoppingCartItem>>()
    val shoppingCartSharedFlow = _shoppingCartSharedFlow.asSharedFlow()

    var itemDetailsList = mutableListOf<ShoppingCartItem>()
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

    override suspend fun getItem(index: Int): Flow<ShoppingCartItem> = flow {
        shoppingCartSharedFlow.collectLatest {
            emit(it[index])
        }
    }

    override suspend fun updateItem(index: Int, shoppingCartItem: ShoppingCartItem) {
        itemDetailsList[index] = shoppingCartItem
        _shoppingCartSharedFlow.emit(itemDetailsList)
    }
}