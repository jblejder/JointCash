package com.projectblejder.jointcash.presentation.shared.dagger

import android.content.Context
import com.projectblejder.jointcash.infrastructure.AppDatabaseFactory
import com.projectblejder.jointcash.infrastructure.AppDatabaseProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppDatabaseModule {

    @Provides
    @Singleton
    fun dbProvider() = AppDatabaseProvider()

    @Provides
    fun dbFactory(context: Context) = AppDatabaseFactory(context)

    @Provides
    fun db(provider: AppDatabaseProvider) = provider.instance

}
