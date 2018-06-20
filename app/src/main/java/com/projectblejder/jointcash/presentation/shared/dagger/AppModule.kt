package com.projectblejder.jointcash.presentation.shared.dagger

import android.content.Context
import com.projectblejder.jointcash.presentation.shared.GlobalApplication
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {
    @Binds
    abstract fun context(app: GlobalApplication): Context
}
