package com.keisardev.supersmartexample.di

import com.keisardev.supersmartexample.data.ShoppingCartRepositoryImpl
import com.keisardev.supersmartexample.domain.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ShoppingCartModule {

    @Singleton
    @Provides
    fun provideShoppingCartRepository() = ShoppingCartRepositoryImpl()

    @Singleton
    @Provides
    fun provideShoppingCartUseCases(
        addShoppingCartItemUseCase: AddShoppingCartItemUseCase,
        getShoppingCartItemUseCase: GetShoppingCartItemUseCase,
        getShoppingCartListUseCase: GetShoppingCartListUseCase,
        removeShoppingCartItemUseCase: RemoveShoppingCartItemUseCase,
        updateShoppingCartItemUseCase: UpdateShoppingCartItemUseCase
    ) = ShoppingCartUseCases(
        addShoppingCartItemUseCase,
        getShoppingCartItemUseCase,
        getShoppingCartListUseCase,
        removeShoppingCartItemUseCase,
        updateShoppingCartItemUseCase
    )
}