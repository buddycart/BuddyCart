package com.buddycart.buddycart.domain.user

import javax.inject.Inject

class GetAuthenticatedUserInfoFlowUseCase @Inject constructor(
    private val authenticatedUserRepository: AuthenticatedUserRepository
) {
    fun invoke() = authenticatedUserRepository.getAuthenticatedUserFlow()
}