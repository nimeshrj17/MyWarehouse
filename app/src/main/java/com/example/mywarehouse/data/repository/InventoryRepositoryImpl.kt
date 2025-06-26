package com.example.mywarehouse.data.repository

import com.example.mywarehouse.data.local.dao.InventoryDao
import com.example.mywarehouse.data.local.entities.InventoryItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InventoryRepositoryImpl @Inject constructor(
    private val dao: InventoryDao
) : InventoryRepository {

    override suspend fun insertItem(item: InventoryItem) {
        dao.insertInventoryItem(item)
    }

    override suspend fun deleteItem(item: InventoryItem) {
        dao.deleteInventoryItem(item)
    }

    override suspend fun updateItem(item: InventoryItem) {
        dao.updateInventoryItem(item)
    }

    override fun getAllItems(): Flow<List<InventoryItem>> {
        return dao.getAllItems()
    }
    override fun getItemById(id: Long): Flow<InventoryItem?> {
        return dao.getItemById(id)
    }
    override fun getLowStockItems(threshold: Int): Flow<List<InventoryItem>> {
        return dao.getLowStockItems(threshold)
    }

}




