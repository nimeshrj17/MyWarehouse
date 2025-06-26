package com.example.mywarehouse.presentation.allscreens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mywarehouse.data.local.entities.InventoryItem
import com.example.mywarehouse.presentation.navigation.Screen
import com.example.mywarehouse.viewmodel.ItemViewModel
import kotlinx.coroutines.launch

@Composable
fun AllProductsScreen(
    navController: NavController,
    itemViewModel: ItemViewModel = hiltViewModel()
) {
    val items by itemViewModel.allItems.collectAsState()
    var sortOption by remember { mutableStateOf("Name") }

    val sortedItems = when (sortOption) {
        "Name" -> items.sortedBy { it.name }
        "Quantity" -> items.sortedBy { it.quantity }
        "Category" -> items.sortedBy { it.category }
        else -> items
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("All Products", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Sort by: ", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.width(8.dp))
            DropdownMenuBox(
                selectedOption = sortOption,
                options = listOf("Name", "Quantity", "Category")
            ) {
                sortOption = it
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(sortedItems, key = { it.id }) { item ->
                var visible by remember { mutableStateOf(true) }

                AnimatedVisibility(
                    visible = visible,
                    exit = fadeOut() + slideOutHorizontally()
                ) {
                    ProductItemCard(
                        item = item,
                        onEditClick = {
                            navController.navigate(Screen.EditItem.createRoute(item.id))
                        },
                        onDeleteClick = {
                            visible = false
                            itemViewModel.deleteItem(item)
                        }
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun ProductItemCard(
    item: InventoryItem,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = item.name, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Category: ${item.category}")
            Text(text = "Quantity: ${item.quantity}")
            Text(text = "Price: ₹${item.price}")

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = onEditClick) {
                    Icon(Icons.Default.Edit, contentDescription = "Edit")
                }
                IconButton(onClick = onDeleteClick) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete")
                }
            }
        }
    }
}

@Composable
fun DropdownMenuBox(
    selectedOption: String,
    options: List<String>,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        OutlinedButton(onClick = { expanded = true }) {
            Text(selectedOption)
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}
