package com.example.mywarehouse.presentation.editscreen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mywarehouse.viewmodel.ItemViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun EditItemScreen(
    itemId: Long,
    navController: NavController,
    itemViewModel: ItemViewModel = hiltViewModel()
) {
    val itemFlow = remember { itemViewModel.getItemById(itemId) }
    val item by itemFlow.collectAsState(initial = null)

    var name by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }

    // Initialize with existing item data
    LaunchedEffect(item) {
        item?.let {
            name = it.name
            quantity = it.quantity.toString()
            price = it.price.toString()
            category = it.category
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Edit Item", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Item Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = quantity,
            onValueChange = { quantity = it },
            label = { Text("Quantity") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = price,
            onValueChange = { price = it },
            label = { Text("Price") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = category,
            onValueChange = { category = it },
            label = { Text("Category") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val updatedItem = item?.copy(
                    name = name,
                    quantity = quantity.toIntOrNull() ?: 0,
                    price = price.toDoubleOrNull() ?: 0.0,
                    category = category
                )

                updatedItem?.let {
                    itemViewModel.updateItem(it)
                    navController.popBackStack() // Go back after update
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Changes")
        }
    }
}
