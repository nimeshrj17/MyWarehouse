package com.example.mywarehouse.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mywarehouse.data.local.entities.InventoryItem
import com.example.mywarehouse.data.local.dao.InventoryDao


@Database
    (
    entities = [InventoryItem::class],
    version = 1,
    exportSchema = false
            )
abstract class InventoryDatabase : RoomDatabase() {
    abstract fun inventoryDao(): InventoryDao
}