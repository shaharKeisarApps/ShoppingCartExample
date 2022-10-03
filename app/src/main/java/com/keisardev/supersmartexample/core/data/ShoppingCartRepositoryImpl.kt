package com.keisardev.supersmartexample.core.data

import android.util.Log
import com.keisardev.supersmartexample.core.domain.abstractions.ShoppingCartRepository
import com.keisardev.supersmartexample.feature_list.domain.entity.ShoppingCartItem
import kotlinx.coroutines.flow.*


class ShoppingCartRepositoryImpl : ShoppingCartRepository {
    private val _shoppingCartSharedFlow =
        MutableSharedFlow<List<ShoppingCartItem>>()
    val shoppingCartSharedFlow = _shoppingCartSharedFlow.asSharedFlow()

    var itemDetailsList = mutableListOf<ShoppingCartItem>()

    var index: Int = 0

    override suspend fun getShoppingCartList(): Flow<List<ShoppingCartItem>> = channelFlow {
        shoppingCartSharedFlow.collectLatest {
            send(it.asReversed())
        }
    }

    override suspend fun addItem(shoppingCartItem: ShoppingCartItem) {
        itemDetailsList.add(shoppingCartItem)
        index += 1
        _shoppingCartSharedFlow.emit(itemDetailsList)
    }

    override suspend fun removeItem(shoppingCartItem: ShoppingCartItem) {
        if (itemDetailsList.remove(shoppingCartItem))
        this.index -= 1
        _shoppingCartSharedFlow.emit(itemDetailsList)
    }

    override suspend fun getItem(index: Int): Flow<ShoppingCartItem> = channelFlow {
        Log.d("getItem", "$index")
        send(itemDetailsList[index])
    }

    override suspend fun updateItem( shoppingCartItem: ShoppingCartItem) {
        itemDetailsList[shoppingCartItem.index.dec()] = shoppingCartItem
        _shoppingCartSharedFlow.emit(itemDetailsList)
    }



    override fun getIndexOfItem(): Int {
        return index
    }

}