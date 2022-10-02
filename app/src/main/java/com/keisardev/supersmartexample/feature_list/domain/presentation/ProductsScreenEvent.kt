package com.keisardev.supersmartexample.feature_list.domain.presentation

import com.keisardev.supersmartexample.feature_add_item.ItemTypeState
import com.keisardev.supersmartexample.feature_list.domain.DialogProductModel
import com.keisardev.supersmartexample.feature_list.domain.ListItemUIModel

sealed class ProductsScreenEvent{
    object AddButtonClicked : ProductsScreenEvent()
    data class AddProduct(val itemToAdd : DialogProductModel) : ProductsScreenEvent()
    data class ProductClicked(val index: Int) : ProductsScreenEvent()
}
