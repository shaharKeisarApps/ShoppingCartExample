package com.keisardev.supersmartexample.feature_list.domain.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.keisardev.supersmartexample.R
import com.keisardev.supersmartexample.feature_list.domain.ListItemUIModel
import com.keisardev.supersmartexample.feature_list.domain.ProductsScreenViewModel
import com.keisardev.supersmartexample.feature_list.domain.presentation.components.AddProductDialog
import com.keisardev.supersmartexample.feature_list.domain.presentation.components.ProductItem
import com.keisardev.supersmartexample.feature_list.domain.presentation.components.ProductItemPreview.uiModelList
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@RootNavGraph(start = true)
@Destination
@Composable
fun ProductsScreen(
    navigator: DestinationsNavigator,

    ) {
    val viewModel = hiltViewModel<ProductsScreenViewModel>()
    viewModel.navigator = navigator
    val state by viewModel.uiState
    val itemIndex = rememberSaveable { mutableStateOf(1) }

    LaunchedEffect(Unit){
        viewModel.collectShoppingCartItems()
    }

    ProductsScreenContent(
        state.products,
        onAddButtonClicked = { viewModel.onEvent(ProductsScreenEvent.AddButtonClicked) }) {
        viewModel.onEvent(
            ProductsScreenEvent.ProductClicked(it.index)
        )
    }
    if (state.showAddProductDialog) {
        AddProductDialog(
            index = itemIndex,
            onDismiss = {},
            onDelete = {},
            onConfirm = {
                viewModel.onEvent(ProductsScreenEvent.AddProduct(it))
                itemIndex.value += 1
            })
    }
}

@Composable
fun ProductsScreenContent(
    productItems: List<ListItemUIModel>,
    onAddButtonClicked: () -> Unit,
    onItemClicked: (ListItemUIModel) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {
        Button(onClick = { onAddButtonClicked() }) {
            Image(
                painter = painterResource(id = R.drawable.ic_plus),
                contentDescription = "add item"
            )
        }
        LazyColumn(contentPadding = PaddingValues(4.dp)) {
            items(productItems) {
                ProductItem(product = it) { onItemClicked(it) }
            }
        }
    }


}


@Preview
@Composable
fun ProductsScreenPreview() {
    ProductsScreenContent(uiModelList, {}, {})
}