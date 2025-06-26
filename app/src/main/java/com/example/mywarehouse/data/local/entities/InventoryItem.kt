package com.example.mywarehouse.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "inventory_items")
data class InventoryItem(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val quantity: Int,
    val price: Double,
    val category : String,
    val timestamp : Long = System.currentTimeMillis(),
    )