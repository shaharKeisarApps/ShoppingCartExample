package com.keisardev.supersmartexample.feature_list.domain.presentation

import com.keisardev.supersmartexample.feature_list.domain.ListItemUIModel

data class ProductsScreenState(
    val products : List<ListItemUIModel> = emptyList(),
    val showAddProductDialog : Boolean = false
)
