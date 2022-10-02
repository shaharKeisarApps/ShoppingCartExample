package com.keisardev.supersmartexample.feature_item_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.keisardev.supersmartexample.feature_list.domain.ShoppingCartItem
import com.keisardev.supersmartexample.navArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShoppingCartItemDetailsViewModel @Inject constructor(
    savedStateHandle : SavedStateHandle
) : ViewModel() {
    private val navArgs: ShoppingCartItem = savedStateHandle.navArgs()

    private val _uiState = mutableStateOf<ShoppingCartItem>(navArgs)
    val uiState : State<ShoppingCartItem> get() = _uiState

}