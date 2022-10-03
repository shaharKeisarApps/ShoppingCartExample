package com.keisardev.supersmartexample.feature_item_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keisardev.supersmartexample.core.domain.usecases.ShoppingCartUseCases
import com.keisardev.supersmartexample.feature_add_item.presentation.DialogProductModel
import com.keisardev.supersmartexample.feature_list.domain.entity.ShoppingCartItem
import com.keisardev.supersmartexample.navArgs
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingCartItemDetailsViewModel @Inject constructor(
    private val shoppingCartUseCases: ShoppingCartUseCases,
    savedStateHandle : SavedStateHandle
) : ViewModel() {
    private val navArgs: ShoppingCartItem = savedStateHandle.navArgs()
    internal lateinit var navigator: DestinationsNavigator

    private val _uiState = mutableStateOf<ShoppingCartItem>(navArgs)
    val uiState : State<ShoppingCartItem> get() = _uiState



    fun onEvent(event: DetailsEvent){
        when (event) {
            DetailsEvent.ExitScreen -> navigator.popBackStack()
            DetailsEvent.RemoveItem -> removeItem()
            is DetailsEvent.UpdateShoppingCartItem -> setNewDetails(event.item)
        }
    }

    private fun removeItem(){
        viewModelScope.launch(Dispatchers.IO) {
            shoppingCartUseCases.removeShoppingCartItemUseCase.invoke(uiState.value)
//            shoppingCartUseCases.updateIndexUseCase.invoke(UpdateIndexEvent.Decrease)

            viewModelScope.launch(Dispatchers.Main){
                navigator.navigateUp()
            }

        }
    }

    private fun setNewDetails(dialogItem: DialogProductModel){
        val newItem = uiState.value.copy(amount = dialogItem.amount, name = dialogItem.name)
        updateShoppingCartItem(newItem)
        viewModelScope.launch(Dispatchers.Main){
            navigator.navigateUp()
        }
    }

    private fun updateShoppingCartItem(newItem: ShoppingCartItem) {
        viewModelScope.launch {
            shoppingCartUseCases.updateShoppingCartItemUseCase.invoke(newItem)
        }
    }

}