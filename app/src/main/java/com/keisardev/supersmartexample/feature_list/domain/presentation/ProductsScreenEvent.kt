package com.keisardev.supersmartexample.feature_list.domain.presentation

import com.keisardev.supersmartexample.feature_list.domain.DialogProductModel

sealed class ProductsScreenEvent{
    object AddButtonClicked : ProductsScreenEvent()
    data class AddProduct(val itemToAdd : DialogProductModel) : ProductsScreenEvent()
    data class ProductClicked(val index: Int) : ProductsScreenEvent()
}
