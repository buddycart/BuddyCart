package com.buddycart.buddycart.data.user

import com.buddycart.buddycart.data.utils.CoroutineDispatcherProvider
import com.buddycart.buddycart.domain.user.AuthenticatedUserRepository
import com.buddycart.buddycart.domain.user.model.AuthenticatedUser
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AuthenticatedUserRepositoryFirebase @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider,
) : AuthenticatedUserRepository {

    override fun getAuthenticatedUserFlow(): Flow<AuthenticatedUser?> = callbackFlow {
        val listener = FirebaseAuth.AuthStateListener {
            trySend(getAuthenticatedUser())
        }
        firebaseAuth.addAuthStateListener(listener)
        awaitClose {
            firebaseAuth.removeAuthStateListener(listener)
        }
    }.flowOn(coroutineDispatcherProvider.io)

    private fun getAuthenticatedUser(): AuthenticatedUser? =
        firebaseAuth.currentUser?.let {
            AuthenticatedUser(
                id = it.uid,
                name = it.displayName ?: return null,
                email = it.email ?: return null,
                photoUrl = it.photoUrl?.toString(),
            )
        }
}