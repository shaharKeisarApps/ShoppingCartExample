package com.keisardev.supersmartexample.domain.usecases

import com.keisardev.supersmartexample.domain.abstractions.ShoppingCartRepository
import com.keisardev.supersmartexample.feature_list.domain.ShoppingCartItem
import javax.inject.Inject

class AddShoppingCartItemUseCase @Inject constructor(
    private val shoppingCartRepository: ShoppingCartRepository
){
    suspend operator fun invoke(itemDetails: ShoppingCartItem) = shoppingCartRepository.addItem(itemDetails)
}