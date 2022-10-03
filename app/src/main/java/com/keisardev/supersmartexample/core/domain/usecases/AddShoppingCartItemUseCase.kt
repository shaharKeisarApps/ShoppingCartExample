package com.keisardev.supersmartexample.core.domain.usecases

import com.keisardev.supersmartexample.core.domain.abstractions.ShoppingCartRepository
import com.keisardev.supersmartexample.feature_list.domain.entity.ShoppingCartItem
import javax.inject.Inject

class AddShoppingCartItemUseCase @Inject constructor(
    private val shoppingCartRepository: ShoppingCartRepository
){
    suspend operator fun invoke(itemDetails: ShoppingCartItem) = shoppingCartRepository.addItem(itemDetails)
}