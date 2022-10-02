package com.keisardev.supersmartexample.domain.usecases

import com.keisardev.supersmartexample.domain.abstractions.ShoppingCartRepository
import javax.inject.Inject

class GetShoppingCartItemUseCase @Inject constructor(
    private val shoppingCartRepository: ShoppingCartRepository
){
    suspend operator fun invoke(index: Int) = shoppingCartRepository.getItem(index)
}