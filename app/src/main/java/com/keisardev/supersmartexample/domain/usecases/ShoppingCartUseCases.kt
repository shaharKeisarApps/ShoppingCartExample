package com.keisardev.supersmartexample.domain.usecases

data class ShoppingCartUseCases(
    val addShoppingCartItemUseCase: AddShoppingCartItemUseCase,
    val getShoppingCartItemUseCase: GetShoppingCartItemUseCase,
    val getShoppingCartListUseCase: GetShoppingCartListUseCase,
    val removeShoppingCartItemUseCase: RemoveShoppingCartItemUseCase,
    val updateShoppingCartItemUseCase: UpdateShoppingCartItemUseCase
)
