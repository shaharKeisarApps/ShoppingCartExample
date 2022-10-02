package com.keisardev.supersmartexample.feature_item_details

import com.keisardev.supersmartexample.feature_list.domain.DialogProductModel
import com.keisardev.supersmartexample.feature_list.domain.ShoppingCartItem

sealed class DetailsEvent {
    data class UpdateShoppingCartItem(val item: DialogProductModel) : DetailsEvent()
    object RemoveItem : DetailsEvent()
    object ExitScreen : DetailsEvent()
}