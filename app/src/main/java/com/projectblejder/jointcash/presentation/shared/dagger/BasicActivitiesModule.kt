package com.projectblejder.jointcash.presentation.shared.dagger

import com.projectblejder.jointcash.presentation.MainActivity
import com.projectblejder.jointcash.presentation.groups.CreateGroupActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BasicActivitiesModule {

    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun createGroupActivity(): CreateGroupActivity
}
