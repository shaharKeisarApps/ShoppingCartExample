package com.keisardev.supersmartexample.feature_item_details

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.keisardev.supersmartexample.feature_list.domain.ShoppingCartItem
import com.ramcosta.composedestinations.annotation.Destination

@Destination(
    navArgsDelegate = ShoppingCartItem::class
)
@Composable
fun ItemDetailsScreen () {
    val viewModel = hiltViewModel<ShoppingCartItemDetailsViewModel>()
//    val state by viewModel.uiState
//
//    ItemDetailsScreenContent(item = state)


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