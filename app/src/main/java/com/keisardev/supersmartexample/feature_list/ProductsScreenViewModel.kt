package com.keisardev.supersmartexample.feature_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keisardev.supersmartexample.destinations.ItemDetailsScreenDestination
import com.keisardev.supersmartexample.core.domain.usecases.ShoppingCartUseCases
import com.keisardev.supersmartexample.feature_list.domain.entity.ListItemUIModel
import com.keisardev.supersmartexample.feature_list.domain.entity.ShoppingCartItem
import com.keisardev.supersmartexample.feature_list.presentation.ProductsScreenEvent
import com.keisardev.supersmartexample.feature_list.presentation.ProductsScreenState
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import java.util.UUID.randomUUID
import javax.inject.Inject

@HiltViewModel
class ProductsScreenViewModel @Inject constructor(
    private val shoppingCartUseCases: ShoppingCartUseCases
) : ViewModel() {
    internal lateinit var navigator: DestinationsNavigator

    private val _uiState = mutableStateOf(ProductsScreenState())
    val uiState: State<ProductsScreenState> get() = _uiState

    fun collectShoppingCartItems() {
        viewModelScope.launch(Dispatchers.Default) {
            val listFlow = shoppingCartUseCases.getShoppingCartListUseCase.invoke()
            listFlow.collectLatest { shoppingCartItems ->
                _uiState.value = uiState.value.copy(products = shoppingCartItems.map {
                    ListItemUIModel(shoppingCartItems.asReversed().indexOf(it).inc(), it.name, it.price)
                })
            }
        }
    }

    fun onEvent(event: ProductsScreenEvent) {
        when (event) {
            is ProductsScreenEvent.AddProduct -> {
                val itemDetails = event.itemToAdd
                createNewItem(itemDetails.name, itemDetails.amount)
            }

            is ProductsScreenEvent.ProductClicked -> navigateToDetails(event.index)
            ProductsScreenEvent.AddButtonClicked -> _uiState.value =
                uiState.value.copy(showAddProductDialog = true)

            ProductsScreenEvent.DismissClicked -> _uiState.value =
                uiState.value.copy(showAddProductDialog = false)
        }
    }

    private fun createNewItem(name: String, amount: String) {
        val price = generatePrice()
        val shoppingCartItem = ShoppingCartItem(
            index = getIndex(),
            amount = amount,
            name = name,
            price = price,
            barcode = generateBarcode(),
            description = generateDescription()
        )
        viewModelScope.launch {
            shoppingCartUseCases.addShoppingCartItemUseCase(shoppingCartItem)
            _uiState.value = uiState.value.copy(showAddProductDialog = false)
        }


    }

    private fun getIndex() = shoppingCartUseCases.getIndexUseCase.invoke() + 1

    private fun generateDescription(): String {
        val descriptionDummyList = listOf(
            "Healthy and delicious",
            "Sweet taste",
            "Handcrafted by our most professional people"
        )

        val randomIndex = (0..2).random()

        return descriptionDummyList[randomIndex]
    }

    private fun generateBarcode(): String {
        return randomUUID().toString()
    }

    private fun generatePrice(): String {
        val firstDigit = (0..100).random()
        val smallNumber = DecimalFormat("0.00")
        val decimalDigits = smallNumber.format(Math.random())
        return "$$firstDigit${decimalDigits}"
    }

    private fun navigateToDetails(index: Int) {
        viewModelScope.launch(Dispatchers.Default) {
            val item = shoppingCartUseCases.getShoppingCartItemUseCase.invoke(index).first()
            viewModelScope.launch(Dispatchers.Main){
                navigator.navigate(
                    ItemDetailsScreenDestination(item)
                )
            }

        }

    }
}