package com.example.mywarehouse.data.local.dao

import androidx.room.OnConflictStrategy
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.mywarehouse.data.local.entities.InventoryItem
import kotlinx.coroutines.flow.Flow



@Dao
interface  InventoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInventoryItem(item: InventoryItem)

    @Update
    suspend fun updateInventoryItem(item: InventoryItem)

    @Delete
    suspend fun deleteInventoryItem(item: InventoryItem)

    @Query("DELETE FROM inventory_items")
    suspend fun deleteAllItems()

    @Query("SELECT * FROM inventory_items ORDER BY timestamp DESC")
    fun getAllItems(): Flow<List<InventoryItem>>

    @Query("SELECT * FROM inventory_items WHERE id = :id")
    suspend fun getItemById(id: Int): InventoryItem?

    @Query("SELECT * FROM inventory_items WHERE id = :id")
    fun getItemById(id: Long): Flow<InventoryItem?>

    @Query("SELECT * FROM inventory_items WHERE quantity < :threshold ORDER BY quantity ASC")
    fun getLowStockItems(threshold: Int = 5): Flow<List<InventoryItem>>

}
