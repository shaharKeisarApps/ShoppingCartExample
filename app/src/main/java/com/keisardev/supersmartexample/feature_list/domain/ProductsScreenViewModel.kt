package com.keisardev.supersmartexample.feature_list.domain

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keisardev.supersmartexample.destinations.ItemDetailsScreenDestination
import com.keisardev.supersmartexample.domain.usecases.ShoppingCartUseCases
import com.keisardev.supersmartexample.feature_list.domain.presentation.ProductsScreenEvent
import com.keisardev.supersmartexample.feature_list.domain.presentation.ProductsScreenState
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.math.MathContext
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

    var itemList = mutableListOf<ListItemUIModel>()

    var itemIndex = 0

    fun collectShoppingCartItems() {
        viewModelScope.launch(Dispatchers.Default) {
            val listFlow = shoppingCartUseCases.getShoppingCartListUseCase.invoke()
            listFlow.collectLatest { shoppingCartItems ->
                _uiState.value = uiState.value.copy(products = shoppingCartItems.map {
                    ListItemUIModel(it.index, it.name, it.price)
                })
            }
        }
    }

    fun onEvent(event: ProductsScreenEvent) {
        when (event) {
            is ProductsScreenEvent.AddProduct -> {
                itemIndex += 1
                val itemDetails = event.itemToAdd
                createNewItem(itemDetails.name, itemDetails.amount)
            }

            is ProductsScreenEvent.ProductClicked -> navigateToDetails(event.index)
            ProductsScreenEvent.AddButtonClicked -> _uiState.value =
                uiState.value.copy(showAddProductDialog = true)
        }
    }

    private fun createNewItem(name: String, amount: String) {
        val price = generatePrice()
        val shoppingCartItem = ShoppingCartItem(
            index = itemIndex,
            amount = amount,
            name = name,
            price = price,
            barcode = generateBarcode(),
            description = generateDescription()
        )
        viewModelScope.launch {
            itemList.add(ListItemUIModel(itemIndex, name, price))
            shoppingCartUseCases.addShoppingCartItemUseCase(shoppingCartItem)
            _uiState.value = uiState.value.copy(showAddProductDialog = false)
        }


    }

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
            val itemFlow = shoppingCartUseCases.getShoppingCartItemUseCase.invoke(index)
            itemFlow.collectLatest {
                navigator.navigate(
                    ItemDetailsScreenDestination(it)
                )
            }

        }

    }
}