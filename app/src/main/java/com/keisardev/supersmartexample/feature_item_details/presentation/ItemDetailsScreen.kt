package com.keisardev.supersmartexample.feature_item_details.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.keisardev.supersmartexample.feature_item_details.DetailsEvent
import com.keisardev.supersmartexample.feature_item_details.ShoppingCartItemDetailsViewModel
import com.keisardev.supersmartexample.feature_list.domain.entity.ShoppingCartItem
import com.keisardev.supersmartexample.feature_list.domain.presentation.components.AddProductDialog
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(
    navArgsDelegate = ShoppingCartItem::class
)
@Composable
fun ItemDetailsScreen (
    navigator: DestinationsNavigator
) {
    val viewModel = hiltViewModel<ShoppingCartItemDetailsViewModel>()
    viewModel.navigator = navigator
    val state by viewModel.uiState


    val openDialog = remember { mutableStateOf(true)  }
    if (openDialog.value){
        AddProductDialog(
            state.name,
            state.amount,
            index = state.index,
            onDismiss = {navigator.navigateUp()},
            onDelete = {
                openDialog.value = false
                viewModel.onEvent(DetailsEvent.RemoveItem)
                       },
            onConfirm = {
                viewModel.onEvent(DetailsEvent.UpdateShoppingCartItem(it))
            })

    }


}

@Composable
fun ItemDetailsScreenContent (item: ShoppingCartItem) {
    Column(){
        Text(item.barcode)
        Text(item.description)

    }
}

@Preview
@Composable fun ItemDetailsScreenPreview () {

}