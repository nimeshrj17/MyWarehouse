package com.example.mywarehouse.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mywarehouse.data.local.entities.InventoryItem
import com.example.mywarehouse.data.repository.InventoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InventoryViewModel @Inject constructor(
    private val repository: InventoryRepository
) : ViewModel() {

    private val _inventoryItems = MutableStateFlow<List<InventoryItem>>(emptyList())
    val inventoryItems: StateFlow<List<InventoryItem>> = _inventoryItems.asStateFlow()

    init {
        getAllItems()
    }

    private fun getAllItems() {
        viewModelScope.launch {
            repository.getAllItems()
                .collect { items ->
                    _inventoryItems.value = items
                }
        }
    }

    fun addItem(item: InventoryItem) {
        viewModelScope.launch {
            repository.insertItem(item)
        }
    }

    fun deleteItem(item: InventoryItem) {
        viewModelScope.launch {
            repository.deleteItem(item)
        }
    }

    fun updateItem(item: InventoryItem) {
        viewModelScope.launch {
            repository.updateItem(item)
        }
    }
}