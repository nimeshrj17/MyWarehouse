package com.example.mywarehouse.presentation.navigation

sealed class Screen(val route: String) {
    object Register : Screen("register")
    object Home : Screen("home")
    object Login : Screen("login")
    object AddItem : Screen("add_item")
    object AllProducts : Screen("all_products")
    object LowStock : Screen("low_stock")
    object Profile : Screen("profile")
    object AuthGate : Screen("auth_gate")
    object EditItem : Screen("edit_item/{itemId}") {
        fun createRoute(itemId: Long) = "edit_item/$itemId"
    }

}

