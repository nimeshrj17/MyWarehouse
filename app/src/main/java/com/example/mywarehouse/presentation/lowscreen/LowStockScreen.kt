package com.example.mywarehouse.presentation.lowscreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mywarehouse.viewmodel.ItemViewModel
import com.example.mywarehouse.data.local.entities.InventoryItem

@Composable
fun LowStockScreen(
    navController: NavController,
    itemViewModel: ItemViewModel = hiltViewModel()
) {
    val lowStockItems by itemViewModel.lowStockItems.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Text("Low Stock Items", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        if (lowStockItems.isEmpty()) {
            Text("All items are well stocked.", style = MaterialTheme.typography.bodyMedium)
        } else {
            LazyColumn {
                items(lowStockItems) { item ->
                    LowStockItemCard(item = item)
                }
            }
        }
    }
}

@Composable
fun LowStockItemCard(item: InventoryItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = item.name, style = MaterialTheme.typography.titleMedium)
            Text(text = "Category: ${item.category}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Quantity: ${item.quantity}", style = MaterialTheme.typography.bodySmall)
        }
    }
}
