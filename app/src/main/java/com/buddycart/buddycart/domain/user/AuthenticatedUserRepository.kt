package com.buddycart.buddycart.domain.user

import com.buddycart.buddycart.domain.user.model.AuthenticatedUser
import kotlinx.coroutines.flow.Flow

interface AuthenticatedUserRepository {
fun getAuthenticatedUserFlow() : Flow<AuthenticatedUser?>
}
