package com.example.mywarehouse.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mywarehouse.presentation.navigation.Screen
import com.example.mywarehouse.viewmodel.AuthViewModel

@Composable
fun AuthGate(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel() ,

) {
    val user = viewModel.auth.currentUser

    LaunchedEffect(user) {
        if (user != null) {
            navController.navigate(Screen.Home.route) {
                popUpTo(0) // clears backstack
            }
        } else {
            navController.navigate(Screen.Login.route) {
                popUpTo(0)
            }
        }
    }
}