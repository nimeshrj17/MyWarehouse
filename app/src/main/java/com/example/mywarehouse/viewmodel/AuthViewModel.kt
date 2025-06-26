package com.example.mywarehouse.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.mywarehouse.auth.AuthRepository
import com.example.mywarehouse.presentation.navigation.Screen
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
     val auth: FirebaseAuth

) : ViewModel() {

    private val _loginState = MutableStateFlow(LoginState())
    val loginState = _loginState.asStateFlow()

    fun loginWithEmailPassword(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _loginState.value = LoginState(isSuccess = true)
                } else {
                    _loginState.value = LoginState(error = task.exception?.message)
                }
            }
    }
    private val _registerState = MutableStateFlow(RegisterState())
    val registerState = _registerState.asStateFlow()

    fun registerWithEmailPassword(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("AuthViewModel", "Registration successful")
                    _registerState.value = RegisterState(isSuccess = true)
                } else {
                    _registerState.value = RegisterState(error = task.exception?.message)
                }
            }
    }
    fun logout() {
        auth.signOut()
    }

}

data class LoginState(
    val isSuccess: Boolean = false,
    val error: String? = null
)
data class RegisterState(
    val isSuccess: Boolean = false,
    val error: String? = null
)


