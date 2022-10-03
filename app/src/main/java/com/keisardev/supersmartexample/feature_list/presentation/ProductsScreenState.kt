package com.keisardev.supersmartexample.feature_list.presentation

import com.keisardev.supersmartexample.feature_list.domain.entity.ListItemUIModel

data class ProductsScreenState(
    val products : List<ListItemUIModel> = emptyList(),
    val showAddProductDialog : Boolean = false
)
