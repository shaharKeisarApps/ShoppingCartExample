package com.keisardev.supersmartexample.feature_add_item

sealed interface ItemTypeState{
    object QuantityItem : ItemTypeState
    object WeightItem : ItemTypeState
}