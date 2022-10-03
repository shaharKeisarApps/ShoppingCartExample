package com.keisardev.supersmartexample.feature_list.presentation.components

import com.keisardev.supersmartexample.feature_list.domain.entity.ListItemUIModel

object ProductItemPreview {
    val avocado = ListItemUIModel(
        index = 1,
        name = "Avocado",
        price = "$1.70"
    )

    val melon = ListItemUIModel(
        index = 2,
        name = "Melon",
        price = "$3.50"
    )

    val peach = ListItemUIModel(
        index = 7,
        name = "Peach",
        price = "$2.70"
    )


    val uiModelList = listOf(avocado, melon, peach)

}