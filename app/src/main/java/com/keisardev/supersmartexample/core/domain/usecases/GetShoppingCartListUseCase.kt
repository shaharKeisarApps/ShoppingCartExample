package com.keisardev.supersmartexample.core.domain.usecases

import com.keisardev.supersmartexample.core.domain.abstractions.ShoppingCartRepository
import javax.inject.Inject

class GetShoppingCartListUseCase @Inject constructor(
    private val shoppingCartRepository: ShoppingCartRepository
){
    suspend operator fun invoke() = shoppingCartRepository.getShoppingCartList()
}