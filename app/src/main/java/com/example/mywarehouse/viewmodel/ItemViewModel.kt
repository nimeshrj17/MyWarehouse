package com.example.mywarehouse.viewmodel

import android.R.attr.category
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mywarehouse.data.local.entities.InventoryItem
import com.example.mywarehouse.data.repository.InventoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    private val repository: InventoryRepository
) : ViewModel() {


    val items = repository.getAllItems()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allItems = repository.getAllItems().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    val lowStockItems = repository.getLowStockItems()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    fun addItem(name: String, quantity: Int, price: Double) {
        viewModelScope.launch {
            repository.insertItem(InventoryItem(name = name, quantity = quantity, price = price , category = category.toString()))
        }
    }

    fun deleteItem(item: InventoryItem) {
        viewModelScope.launch {
            repository.deleteItem(item)
        }
    }

    fun getItemById(id: Long) = repository.getItemById(id)


    fun updateItem(item: InventoryItem) {
        viewModelScope.launch {
            repository.updateItem(item)
        }
    }

}
