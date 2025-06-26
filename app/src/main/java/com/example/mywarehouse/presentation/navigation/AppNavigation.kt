package com.example.mywarehouse.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mywarehouse.presentation.AuthGate
import com.example.mywarehouse.presentation.additemscreen.AddItemScreen
import com.example.mywarehouse.presentation.allscreens.AllProductsScreen
import com.example.mywarehouse.presentation.editscreen.EditItemScreen
import com.example.mywarehouse.presentation.registerscreen.RegisterScreen
import com.example.mywarehouse.presentation.homescreen.HomeScreen
import com.example.mywarehouse.presentation.login.LoginScreen
import com.example.mywarehouse.presentation.lowscreen.LowStockScreen

@Composable
fun MyWarehouseNavGraph(startDestination: String = Screen.Login.route) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination =   "auth_gate"
    ) {
        composable(Screen.Login.route) {
            LoginScreen(navController = navController)
        }

        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(Screen.Register.route) {
            RegisterScreen(navController = navController)
        }
        composable("auth_gate") {
            AuthGate(navController)
        }
        composable(Screen.AddItem.route) {
            AddItemScreen(navController = navController)
        }
        composable(
            route = Screen.EditItem.route,
            arguments = listOf(navArgument("itemId") { type = NavType.LongType })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getLong("itemId") ?: 0L
            EditItemScreen(itemId = itemId, navController = navController)
        }

        composable(Screen.LowStock.route) {
            LowStockScreen(navController = navController)
        }
        composable(Screen.AllProducts.route) {
            AllProductsScreen(navController = navController)
        }

    }
}
