package com.example.mywarehouse.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) {
    fun getCurrentUser() = firebaseAuth.currentUser

    suspend fun registerUser(email: String, password: String): Result<Unit> = try {
        firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun loginUser(email: String, password: String): Result<Unit> = try {
        firebaseAuth.signInWithEmailAndPassword(email, password).await()
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    fun logout() = firebaseAuth.signOut()

    suspend fun firebaseAuthWithGoogle(idToken: String): Result<Unit> = try {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(credential).await()
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }
}