package com.keisardev.supersmartexample.core.di

import com.keisardev.supersmartexample.core.domain.abstractions.ShoppingCartRepository
import com.keisardev.supersmartexample.core.domain.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ShoppingCartUseCasesModule {

    @Singleton
    @Provides
    fun provideAddShoppingCartItemUseCase(shoppingCartRepository: ShoppingCartRepository) =
        AddShoppingCartItemUseCase(shoppingCartRepository)

    @Singleton
    @Provides
    fun provideGetShoppingCartItemUseCase(shoppingCartRepository: ShoppingCartRepository) =
        GetShoppingCartItemUseCase(shoppingCartRepository)

    @Singleton
    @Provides
    fun provideGetShoppingCartListUseCase(shoppingCartRepository: ShoppingCartRepository) =
        GetShoppingCartListUseCase(shoppingCartRepository)

    @Singleton
    @Provides
    fun provideRemoveShoppingCartItemUseCase(shoppingCartRepository: ShoppingCartRepository) =
        RemoveShoppingCartItemUseCase(shoppingCartRepository)

    @Singleton
    @Provides
    fun provideUpdateShoppingCartItemUseCase(shoppingCartRepository: ShoppingCartRepository) =
        UpdateShoppingCartItemUseCase(shoppingCartRepository)


}