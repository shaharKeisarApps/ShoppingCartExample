package com.keisardev.supersmartexample.core.domain.usecases

data class ShoppingCartUseCases(
    val addShoppingCartItemUseCase: AddShoppingCartItemUseCase,
    val getShoppingCartItemUseCase: GetShoppingCartItemUseCase,
    val getShoppingCartListUseCase: GetShoppingCartListUseCase,
    val removeShoppingCartItemUseCase: RemoveShoppingCartItemUseCase,
    val updateShoppingCartItemUseCase: UpdateShoppingCartItemUseCase,
    val getIndexUseCase: GetIndexUseCase
)
