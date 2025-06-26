package com.example.mywarehouse.presentation.homescreen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mywarehouse.presentation.navigation.Screen
import com.example.mywarehouse.viewmodel.AuthViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val user = viewModel.auth.currentUser

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome, ${user?.email ?: "User"}",
            style = MaterialTheme.typography.headlineSmall
        )

        Button(
            onClick = { navController.navigate(Screen.AddItem.route) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("➕ Add Item")
        }

        Button(
            onClick = { navController.navigate(Screen.AllProducts.route) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("📦 All Products")
        }

        Button(
            onClick = { navController.navigate(Screen.LowStock.route) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("⚠️ Low Stock")
        }

        Button(
            onClick = {
                viewModel.logout()
                navController.navigate("auth_gate") {
                    popUpTo(0) { inclusive = true }
                }
            },
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("🚪 Logout")
        }
    }
}
