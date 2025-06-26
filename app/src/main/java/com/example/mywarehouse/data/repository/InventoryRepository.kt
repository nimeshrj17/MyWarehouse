package com.example.mywarehouse.data.repository

import com.example.mywarehouse.data.local.entities.InventoryItem
import kotlinx.coroutines.flow.Flow

interface InventoryRepository {
    suspend fun insertItem(item: InventoryItem)
    suspend fun deleteItem(item: InventoryItem)
    fun getAllItems(): Flow<List<InventoryItem>>
    suspend fun updateItem(item: InventoryItem)
    fun getItemById(id: Long): Flow<InventoryItem?>
    fun getLowStockItems(threshold: Int = 5): Flow<List<InventoryItem>>
}
