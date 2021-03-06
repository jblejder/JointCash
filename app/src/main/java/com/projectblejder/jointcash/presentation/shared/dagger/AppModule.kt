package com.projectblejder.jointcash.presentation.shared.dagger

import android.content.Context
import com.projectblejder.jointcash.presentation.shared.GlobalApplication
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class AppModuleBinds {

    @Binds
    abstract fun context(app: GlobalApplication): Context

}

@Module
class AppModule {

    @Provides
    fun resources(context: Context) = context.resources
}
