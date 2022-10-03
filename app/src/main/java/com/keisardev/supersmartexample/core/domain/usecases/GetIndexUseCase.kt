package com.keisardev.supersmartexample.core.domain.usecases

import com.keisardev.supersmartexample.core.domain.abstractions.ShoppingCartRepository
import javax.inject.Inject

class GetIndexUseCase @Inject constructor(
    private val shoppingCartRepository: ShoppingCartRepository
) {
    operator fun invoke() = shoppingCartRepository.getIndexOfItem()
}