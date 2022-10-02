package com.keisardev.supersmartexample.feature_item_details

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.keisardev.supersmartexample.feature_list.domain.ShoppingCartItem
import com.keisardev.supersmartexample.feature_list.domain.presentation.ProductsScreenEvent
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


//
//    ItemDetailsScreenContent(item = state)

    AddProductDialog(
        index = state.index,
        onDismiss = {},
        onDelete = {},
        onConfirm = {
            viewModel.onEvent(DetailsEvent.UpdateShoppingCartItem(it))
        })


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