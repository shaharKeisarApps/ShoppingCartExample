package com.keisardev.supersmartexample.feature_list.presentation

import com.keisardev.supersmartexample.feature_add_item.presentation.DialogProductModel

sealed class ProductsScreenEvent{
    object AddButtonClicked : ProductsScreenEvent()
    object DismissClicked : ProductsScreenEvent()
    data class AddProduct(val itemToAdd : DialogProductModel) : ProductsScreenEvent()
    data class ProductClicked(val index: Int) : ProductsScreenEvent()
}
