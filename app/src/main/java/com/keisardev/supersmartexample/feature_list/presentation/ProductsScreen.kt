package com.keisardev.supersmartexample.feature_list.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.keisardev.supersmartexample.R
import com.keisardev.supersmartexample.feature_list.domain.entity.ListItemUIModel
import com.keisardev.supersmartexample.feature_list.ProductsScreenViewModel
import com.keisardev.supersmartexample.feature_list.domain.presentation.components.AddProductDialog
import com.keisardev.supersmartexample.feature_list.presentation.components.ProductItem
import com.keisardev.supersmartexample.feature_list.presentation.components.ProductItemPreview.uiModelList
import com.keisardev.supersmartexample.ui.theme.Colors
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

    LaunchedEffect(Unit) {
        viewModel.collectShoppingCartItems()
    }

    ProductsScreenContent(
        state.products,
        onAddButtonClicked = { viewModel.onEvent(ProductsScreenEvent.AddButtonClicked) },
        onItemClicked = {
            viewModel.onEvent(
                ProductsScreenEvent.ProductClicked(state.products.asReversed().indexOf(it))
            )
        })
    if (state.showAddProductDialog) {
        AddProductDialog(
            index = state.products.size + 1,
            onDismiss = {viewModel.onEvent(ProductsScreenEvent.DismissClicked)},
            onDelete = {},
            onConfirm = {
                viewModel.onEvent(ProductsScreenEvent.AddProduct(it))
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
        Button(onClick = {onAddButtonClicked()},
            modifier= Modifier.size(64.dp).padding(2.dp), // it is important otherwise the button is oval
            shape = CircleShape,
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.outlinedButtonColors(containerColor = Colors.ExitButton,
                contentColor = Color.Black)
        ) {
            Icon(Icons.Default.Add, contentDescription = "content description", modifier = Modifier.size(48.dp))
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