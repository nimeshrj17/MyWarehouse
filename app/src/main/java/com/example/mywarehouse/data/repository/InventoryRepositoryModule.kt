package com.example.mywarehouse.data.repository

import com.example.mywarehouse.data.local.dao.InventoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InventoryRepositoryModule {

    @Provides
    @Singleton
    fun provideInventoryRepository(
        dao: InventoryDao
    ): InventoryRepository {
        return InventoryRepositoryImpl(dao)
    }
}
