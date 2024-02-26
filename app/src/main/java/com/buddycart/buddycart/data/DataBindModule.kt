package com.buddycart.buddycart.data

import com.buddycart.buddycart.data.user.AuthenticatedUserRepositoryFirebase
import com.buddycart.buddycart.domain.user.AuthenticatedUserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class DataBindModule {

    @Binds
    @Singleton
    abstract fun provideAuthenticatedUserRepository(
        authenticatedUserRepositoryFirebase: AuthenticatedUserRepositoryFirebase
    ): AuthenticatedUserRepository
}