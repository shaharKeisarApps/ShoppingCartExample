package com.keisardev.supersmartexample.di

import com.keisardev.supersmartexample.data.ShoppingCartRepositoryImpl
import com.keisardev.supersmartexample.domain.abstractions.ShoppingCartRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ShoppingCartBinder {

    @Binds
    abstract fun bindShoppingCartRepository(shoppingCartRepositoryImpl: ShoppingCartRepositoryImpl): ShoppingCartRepository
}