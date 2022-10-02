package com.keisardev.supersmartexample.core.domain.usecases

import com.keisardev.supersmartexample.core.domain.abstractions.ShoppingCartRepository
import com.keisardev.supersmartexample.feature_list.domain.ShoppingCartItem
import javax.inject.Inject

class UpdateShoppingCartItemUseCase @Inject constructor(
    private val shoppingCartRepository: ShoppingCartRepository
) {
    suspend operator fun invoke(shoppingCartItem: ShoppingCartItem) =
        shoppingCartRepository.updateItem(shoppingCartItem)
}