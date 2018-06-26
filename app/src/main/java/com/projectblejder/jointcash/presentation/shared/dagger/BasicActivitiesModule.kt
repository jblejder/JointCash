package com.projectblejder.jointcash.presentation.shared.dagger

import com.projectblejder.jointcash.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BasicActivitiesModule {

    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity
}
